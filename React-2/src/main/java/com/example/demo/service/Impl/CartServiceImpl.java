package com.example.demo.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.mapper.CartMapper;
import com.example.demo.mapper.PorderMapper;
import com.example.demo.mapper.ShopMapper;
import com.example.demo.service.CartService;
import com.example.demo.vo.Cart;
import com.example.demo.vo.Porder;
import com.example.demo.vo.Shop;

import jakarta.annotation.Resource;

/**
 * 購物車的實作
 */
@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartMapper cm;

	@Autowired
	private ShopMapper sm;

	@Autowired
	private PorderMapper pm;

	@Value("${spring.mail.username}")
	private String fromEmail;

	@Resource
	private JavaMailSender em;

	/**
	 * 用帳號id找出該帳號的購物車內容
	 */
	@Override
	public List<Cart> queryAll(Integer memberId) {
		List<Cart> l = cm.queryAll(memberId);
		return l;
	}

	/**
	 * 用來更新購物車存的資料
	 * 更具前端傳來的id找到該筆資料 再核對item做增加、減少或刪除
	 */
	@Override
	public void updateCar(Integer id, String item) {
		Shop s = sm.queryShopId(id);
		Porder p = pm.query(s.getPorderId());
		
		if (item.equals("add")) {
			s.setNum(s.getNum() + 1);
			s.setSum(p.getAmount() * s.getNum());
			sm.updateShop(s);
			
		} else if (item.equals("reduced")) {
			s.setNum(s.getNum() - 1);
			if (s.getNum() != 0) { //如果數量-1!=0 則更新sum
				s.setSum(p.getAmount() * s.getNum());
				sm.updateShop(s);
				
			} else { //如果數量=0則刪除
				sm.deleteShop(id);
			}
			
		} else {
			sm.deleteShop(id);
		}
	}

	/**
	 * 刪除該帳號購物車的全部內容 結帳時使用
	 */
	@Override
	public void checkCar(Integer memberId) {
		cm.checkCar(memberId);
	}
	
	/**
	 * 結帳時的信件
	 * @param email
	 */
	public void sendRegistrationEmail(String email,Integer sum) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom(fromEmail);
		msg.setTo(email);
		msg.setSubject("飛龍");
		msg.setText("結帳成功，感謝購買\n"
				+ "總金額"+sum+"元\n"
				+"歡迎再度光臨");

		em.send(msg);
	}

}
