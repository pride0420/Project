package com.example.demo.service;

import java.util.List;

import com.example.demo.vo.LoginRequest;
import com.example.demo.vo.Member;

/**
 * 帳戶的service
 */
public interface MemberService {

	Member queryUser(Member m);
	
	LoginRequest queryUserName(LoginRequest m);

	Member updateMember(Member m);
	
	List<Member> queryAllMember();
	
	void deleteMember(Integer memberId);
	
}
