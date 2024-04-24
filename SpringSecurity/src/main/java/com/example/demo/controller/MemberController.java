package com.example.demo.controller;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.security.LoginRequest;
import com.example.demo.security.LoginResponse;
import com.example.demo.security.identity.MemberIdentity;
import com.example.demo.security.service.TokenService;
import com.example.demo.service.Impl.MemberServiceImpl;
import com.example.demo.vo.Member;

import jakarta.servlet.http.HttpSession;

/**
 * 帳戶的controller
 */
@CrossOrigin(origins = "http://localhost:8080/")
@RestController
@RequestMapping("/member2")
public class MemberController {
	
	private final static Logger log = LoggerFactory.getLogger(Member.class);
	
	@Autowired
	public HttpSession session;

	@Autowired
	public MemberServiceImpl ms;

	@Autowired
    private TokenService ts;
	
	@Autowired
	public MemberIdentity memberIdentity;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	/**
	 * 新增帳號 並發信
	 * @param m
	 * @param bindingResult
	 * @return
	 * @throws IOException
	 */
	@PostMapping("/addMember")
	public boolean addMember(@RequestBody Member m, BindingResult bindingResult) throws IOException {
		boolean x = ms.addMember(m);
		
		if (x) {
			Member mc = ms.queryUserName(m);
			ms.sendRegistrationEmail(mc.getEmail());
		}
		return x;
	}

	/**
	 * 查詢全部帳號
	 * @return
	 */
	@GetMapping("queryAll")
	public ResponseEntity<?> queryAllMember() {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if (authentication != null && authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("admin"))) { 
	        return ResponseEntity.ok("admin");
	    }else if(authentication != null && authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("user"))) {
	    	return ResponseEntity.ok("user");
	    }
	    else {
	        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	    }
	}
	
	/**
	 * 登入
	 * @param request
	 * @return
	 */
	@PostMapping("login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
		LoginResponse res=ts.createToken(request);
		
		return ResponseEntity.ok(res);
	}
	
	/**
	 * 更新token
	 * @param request
	 * @return
	 */
	@PostMapping("/login/refresh-token")
    public ResponseEntity<Map<String, String>> refreshAccessToken(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");
        String accessToken = ts.refreshAccessToken(refreshToken);
        Map<String, String> res = Map.of("accessToken", accessToken);

        return ResponseEntity.ok(res);
    }
	/**
	 * 忘記密碼
	 * @param m
	 * @param bindingResult
	 * @return
	 * @throws IOException
	 */
	@PostMapping("forget")
	public ResponseEntity<?> forgetPassword(@RequestBody Member m, BindingResult bindingResult) throws IOException  {
		String x=ms.forgetPasswordTest(m);
		ms.forgetSendEmail(m.getEmail(), x);
		return ResponseEntity.ok("12");
	}
	/**
	 * 更新密碼
	 * @param m
	 * @return
	 */
	@PostMapping("updatePassword")
	public ResponseEntity<?> updatePassword(@RequestBody Member m){
		boolean x=ms.updatePassword(m);
		
			return ResponseEntity.ok(x);
	}
	
}
