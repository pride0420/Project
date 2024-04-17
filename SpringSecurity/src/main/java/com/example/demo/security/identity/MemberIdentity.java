package com.example.demo.security.identity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.demo.security.details.MemberDetails;
import com.example.demo.vo.Member;

@Component
public class MemberIdentity {
	
	private MemberDetails getMemberDetails() {
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		Object principal=authentication.getPrincipal();
		return "anonymousUser".equals(principal)?new MemberDetails(new Member()):(MemberDetails)principal;
	}

	public Integer getId() {
		return getMemberDetails().getMemberId();
	}
	
	public String Username() {
		return getMemberDetails().getUsername();
	}
	
	public String getRole() {
		return getMemberDetails().getRole();
	}
	
	public boolean isPremium() {
		return getMemberDetails().isPremium();
	}
}
