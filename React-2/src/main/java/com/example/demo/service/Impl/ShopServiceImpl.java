package com.example.demo.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.PorderMapper;
import com.example.demo.mapper.ShopMapper;
import com.example.demo.service.ShopService;
import com.example.demo.vo.Member;
import com.example.demo.vo.Porder;
import com.example.demo.vo.Shop;

import jakarta.servlet.http.HttpSession;

/**
 * 商城的實作
 */
@Service
public class ShopServiceImpl implements ShopService {

	@Autowired
	private ShopMapper sm;

	@Autowired
	private PorderMapper pm;

	@Autowired
	public HttpSession session;

	/**
	 * 該帳號在商城對購物車的新增
	 */
	@Override
	public void addShop(Integer porderId, Integer memberId) {
		Shop s = sm.queryPorderId(porderId,memberId);
		Porder p = pm.query(porderId);
		if (s == null) {
			s = new Shop(memberId, porderId, 1, p.getAmount());
			sm.addShop(s);
		} else {
			s.setNum(s.getNum() + 1);
			s.setSum(p.getAmount() * s.getNum());
			sm.updateShop(s);
		}
	}

	/**
	 * 找出該帳號的購物車金額
	 */
	@Override
	public Integer querySum(Integer memberId) {
		Integer s = sm.querySum(memberId);
		return s != null ? s : 0;
	}

}
