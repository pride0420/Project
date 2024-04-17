package com.example.demo.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.MemberMapper;
import com.example.demo.security.details.MemberDetails;
import com.example.demo.vo.Member;

import jakarta.servlet.http.HttpSession;
@Service 
public class MemberDetailsService implements UserDetailsService{

	@Autowired
	public HttpSession session;
	
	@Autowired
	private MemberMapper mm;
	
	/** 將資料庫的帳錄資料轉為 UserDetails 讓 Spring Security 進行身份驗證和授權*/
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member m=mm.queryMemberUsername(username);
		if(username==null) {
			throw new UsernameNotFoundException("User not found with username: " + username);     
		}
		
		return new MemberDetails(m);
	}
	
}