package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.Impl.AppService;
import com.example.demo.service.Impl.MemberServiceImpl;
import com.example.demo.vo.LoginRequest;
import com.example.demo.vo.LoginResponse;
import com.example.demo.vo.Member;

/**
 * 帳戶的controller
 */
@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/member")
public class MemberController {

	private final static Logger log = LoggerFactory.getLogger(MemberController.class);
	
	private final AppService appService;

    @Autowired
    public MemberController(AppService appService) {
        this.appService = appService;
    }
    
    @Autowired
	public MemberServiceImpl ms;

    /**
     * 登入
     * @param request
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
    	
    	return appService.login(request);
    }
    /**
     * 查詢全部帳號
     * @param token
     * @return
     */
    @GetMapping("queryAll")
    public ResponseEntity<?> queryAllMember(@RequestHeader("Authorization") String token) {
    	
    	return appService.queryAll(token);
    	
    }
    /**
     * 更新密碼
     * @param m
     * @return
     */
    @PostMapping("updatePassword")
    public ResponseEntity<?> updatePassword(@RequestBody Member m) {
    	
    	return appService.updatePassword(m);
    }
    
    /**
	 * 更新帳號資料
	 * @param m
	 * @return
	 */
	@PostMapping("updateMember")
	public Member UpdateMember(@RequestBody Member m) {
		
		return ms.updateMember(m);
	}
	
	/**
	 * 註銷帳號
	 * @param memberId
	 */
	@PostMapping("deleteMember")
	public void deleteMember(Integer memberId) {
		ms.deleteMember(memberId);
	}
	/**
	 * 新增帳號
	 * @param m
	 * @return
	 */
	@PostMapping("addMember")
    public ResponseEntity<?> addMember(@RequestBody Member m) {
    	
    	return appService.addMember(m);
    }
    /**
     * 忘記密碼
     * @param m
     * @return
     */
	@PostMapping("forget")
    public ResponseEntity<?> forgetPassword(@RequestBody Member m) {
    	
    	return appService.forgetPassword(m);
    }
	
}
