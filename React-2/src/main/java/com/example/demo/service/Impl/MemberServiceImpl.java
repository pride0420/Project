package com.example.demo.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.MemberMapper;
import com.example.demo.service.MemberService;
import com.example.demo.vo.LoginRequest;
import com.example.demo.vo.Member;

import jakarta.annotation.Resource;

/**
 * 帳戶的實作
 */
@Service
public class MemberServiceImpl implements MemberService {

	@Value("${spring.mail.username}")
	private String fromEmail;

	@Resource
	private JavaMailSender em;

	@Autowired
	private MemberMapper mm;
	
	/**
	 * 確認帳號密碼
	 */
	@Override
	public Member queryUser(Member m) {
		Member mc = mm.queryUser(m);
		return mc;
	}

	/**
	 * 找出該帳號資料
	 */
	@Override
	public LoginRequest queryUserName(LoginRequest m) {
		LoginRequest mc = mm.queryUserName(m);
		return mc;
	}

	/**
	 * 更新帳號資料
	 */
	@Override
	public Member updateMember(Member m) {
		Member mc = mm.queryUserNameForUpdate(m);
		mc.setName(m.getName());
		mc.setPhone(m.getPhone());
		mc.setEmail(m.getEmail());
		mm.updateMember(mc);

		return mc;
	}

	/**
	 * 註銷帳號
	 */
	@Override
	public void deleteMember(Integer memberId) {
		mm.deleteMember(memberId);
	}

	/**
	 * 查詢全部帳號
	 */
	@Override
	public List<Member> queryAllMember() {
		List<Member> l=mm.queryAllMember();
		return l;
	}
	
}
