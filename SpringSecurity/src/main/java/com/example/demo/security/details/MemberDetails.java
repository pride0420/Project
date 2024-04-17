package com.example.demo.security.details;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.vo.Member;

/**實作 UserDetails 介面  提供給 Spring Security 進行身份驗證和授權。*/
public class MemberDetails implements UserDetails{
	private static final Logger logger = LoggerFactory.getLogger(MemberDetails.class);
	private Member member;

    public MemberDetails(Member member) {
        this.member = member;
    }

    /** 將使用者權限轉換為 Spring Security 中的 GrantedAuthority 物件*/
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        
        return List.of(new SimpleGrantedAuthority(member.getRole()));
        }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getUsername();
    }

	@Override
	public boolean isAccountNonExpired() {
		if (member.getTrailExpiration() == null) {
            return true;
        }

        return LocalDate.now().isBefore(member.getTrailExpiration());
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return member.isEnabled();
	}
	
	public String getRole() {
		return member.getRole();
	}
	
	public Integer getMemberId() {
    	return member.getMemberId();
    }
    public String getName() {
    	return member.getName();
    }
    public String getPhone() {
    	return member.getPhone();
    }
    public String getEmail() {
    	return member.getEmail();
    }
	public boolean isPremium() { return member.isPremium(); }
    public LocalDate getTrailExpiration() { return member.getTrailExpiration(); }
    
}
