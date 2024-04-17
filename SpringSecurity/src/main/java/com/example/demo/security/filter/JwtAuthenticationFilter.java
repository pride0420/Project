package com.example.demo.security.filter;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.security.LoginResponse;
import com.example.demo.security.service.MemberDetailsService;
import com.example.demo.security.service.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
 
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private MemberDetailsService memberDetailsService;
	
	@Autowired
    private UserDetailsService userDetailsService;

	
	private static final Logger log = LoggerFactory.getLogger(TokenService.class);
	
	@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //獲取請求頭Authorization欄位
		String authorizationHeader = request.getHeader("Authorization");
        //如果頭部為Beare 則提取後面字串
        if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7); // 去除 "Bearer " 前缀
            
            	//透過tokenService解析 取出使用者名稱
                Map<String, Object> tokenPayload =  tokenService.parseToken(token);
               
                String username = (String) tokenPayload.get("username");
                
                // 使用用户名加载用户详细信息
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                //將信息封裝成Authentication物件
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                		userDetails,
                        userDetails.getPassword(),
                        userDetails.getAuthorities()
                );
               //設置道SecurityContextHolder 表示已通過
                SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        //將請求和回應傳遞到下一個過濾器
        filterChain.doFilter(request, response);
    }
}
