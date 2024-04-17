package com.example.demo.security.service;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.security.LoginRequest;
import com.example.demo.security.LoginResponse;
import com.example.demo.security.details.MemberDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

/** 用於處理 JWT（JSON Web Token）的生成、解析和刷新*/
@Service
public class TokenService {
	 
	    private  Key secretKey;
	    private  JwtParser jwtParser;
	    
	    @Autowired
	    private  AuthenticationProvider authenticationProvider;
	    
	/** 設定jwt的密鑰跟解析器*/
    @PostConstruct
    private void init() {
        String key = "VincentIsRunningBlogForProgrammingLearner";
        secretKey = Keys.hmacShaKeyFor(key.getBytes());
        jwtParser = Jwts.parserBuilder().setSigningKey(secretKey).build();
    }

    public LoginResponse createToken(LoginRequest request) {
    	
    	 // 將使用者輸入的帳密封裝
        Authentication authToken = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        
        // 執行帳密認證
        authToken = authenticationProvider.authenticate(authToken);
        
        // 認證成功後取得結果
        MemberDetails memberDetails = (MemberDetails) authToken.getPrincipal();
        
        // 產生 token
        String accessToken = createAccessToken(memberDetails.getUsername());
        
        String refreshToken = createRefreshToken(memberDetails.getUsername());
        
        //返回使用者資料
        LoginResponse res = new LoginResponse();
        res.setRefreshToken(refreshToken);
        res.setAccessToken(accessToken);
        res.setMemberId(memberDetails.getMemberId());
        res.setUsername(memberDetails.getUsername());
        res.setPassword(memberDetails.getPassword());
        res.setName(memberDetails.getName());
        res.setPhone(memberDetails.getPhone());
        res.setEmail(memberDetails.getEmail());
        res.setRole(memberDetails.getRole());
        res.setPremium(memberDetails.isPremium());
        res.setTrailExpiration(memberDetails.getTrailExpiration());
        res.setEnabled(memberDetails.isEnabled());
        
        return res;
    }


    private String createAccessToken(String username) {
        // 有效時間（毫秒）
    	//或許當前時間
        long expirationMillis = Instant.now()
        		//將時間後延
                .plusSeconds(9000)
                //獲取後延的時間*1000 轉毫秒
                .getEpochSecond()
                * 1000;

        // 設置標準內容與自定義內容
        Claims claims = Jwts.claims();
        //將主題設為access
        claims.setSubject("Access Token");
        //創建時間 當前時間
        claims.setIssuedAt(new Date());
        //有效時間 加入上面延後的時間
        claims.setExpiration(new Date(expirationMillis));
        //加入自訂義聲明
        claims.put("username", username);

        // 簽名後產生 token
        return Jwts.builder()
        		//加入前面的聲明
                .setClaims(claims)
                //設置密鑰
                .signWith(secretKey)
                //生成字串返回
                .compact();
    }
    
    private String createRefreshToken(String username) {
        long expirationMillis = Instant.now()
                .plusSeconds(9000)
                .getEpochSecond()
                * 1000;

        Claims claims = Jwts.claims();
        claims.setSubject("Refresh Token");
        claims.setIssuedAt(new Date());
        claims.setExpiration(new Date(expirationMillis));
        claims.put("username", username);

        return Jwts.builder()
                .setClaims(claims)
                .signWith(secretKey)
                .compact();
    }
    
    
    public String refreshAccessToken(String refreshToken) {
        Map<String, Object> payload = parseToken(refreshToken);
        String username = (String) payload.get("username");
        return createAccessToken(username);
    }
    /**解析jwt */
    public Map<String, Object> parseToken(String token) {
            Claims claims = jwtParser.parseClaimsJws(token).getBody();
            return new HashMap<>(claims);
    }
}