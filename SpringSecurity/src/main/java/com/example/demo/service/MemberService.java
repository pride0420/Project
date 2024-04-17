package com.example.demo.service;

import java.util.List;

import com.example.demo.vo.Member;

/**
 * 帳戶的service
 */
public interface MemberService {

	boolean addMember(Member m);

	Member queryUser(Member m);
	
	Member queryUserName(Member m);
	
	List<Member> queryAllMember();
	
	String forgetPasswordTest(Member m);

	boolean updatePassword(Member m);
	
}
