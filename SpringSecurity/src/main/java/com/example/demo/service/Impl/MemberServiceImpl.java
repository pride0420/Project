package com.example.demo.service.Impl;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.MemberMapper;
import com.example.demo.service.MemberService;
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
	
	@Autowired
	private PasswordEncoder passwordEncoder;


	/**
	 * 確認帳號密碼
	 */
	@Override
	public Member queryUser(Member m) {
		
		Member mc = mm.queryUser(m);
		return mc;
	}

	/**
	 * 確認帳號有無重複後做新增
	 */
	@Override
	public boolean addMember(Member m) {
		Member mc = mm.queryUserName(m);

		boolean x = false;
		if (mc == null) {
			String encryptedPassword = passwordEncoder.encode(m.getPassword());
	        m.setPassword(encryptedPassword);
			m.setRole("guest");
			mm.addMember(m);
			x = true;
		}
		return x;
	}

	/**
	 * 新增成功後的信件發送
	 * @param email
	 */
	public void sendRegistrationEmail(String email) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom(fromEmail);
		msg.setTo(email);
		msg.setSubject("飛龍");
		msg.setText("註冊成功，感謝註冊");

		em.send(msg);
	}

	/**
	 * 找出該帳號資料
	 */
	@Override
	public Member queryUserName(Member m) {
		Member mc = mm.queryUserName(m);
		return mc;
	}


	/**
	 * 查詢全部帳號
	 */
	@Override
	public List<Member> queryAllMember() {
		List<Member> l=mm.queryAllMember();
		return l;
	}
	
	/**
	 * 寄出新密碼
	 * @param email
	 * @param refeshPas
	 */
	public void forgetSendEmail(String email,String refeshPas) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom(fromEmail);
		msg.setTo(email);
		msg.setSubject("飛龍");
		msg.setText("密碼已更新，新密碼為:"+refeshPas+"，請重新登入後進行修改");

		em.send(msg);
	}

	/**
	 * 忘記密碼
	 */
	@Override
	public String forgetPasswordTest(Member m) {
		Member mc=mm.queryUserAndEmailTest(m);
		String x="";
		if(mc!=null) {
			for(int i=0;i<=3;i++) {
				Random r=new Random();
				x=x+String.valueOf(r.nextInt(10));
			}
		}
		String encryptedPassword = passwordEncoder.encode(x);
		mc.setPassword(encryptedPassword);
		mm.updatePassword(mc);
		
		return x;
	}

	/**
	 * 更新密碼
	 */
	@Override
	public boolean updatePassword(Member m) {
		Member mc=mm.queryUserName(m);
		boolean x=false;
		if(passwordEncoder.matches(m.getPassword(), mc.getPassword())) {
			String encryptedPassword = passwordEncoder.encode(m.getNewPassword());
			mc.setPassword(encryptedPassword);
			mm.updatePassword(mc);
			x=true;
		}
		return x;
	}

}
