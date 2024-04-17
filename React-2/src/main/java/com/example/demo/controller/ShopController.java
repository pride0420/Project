package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.Impl.ShopServiceImpl;
import com.example.demo.vo.Member;

import jakarta.servlet.http.HttpSession;

/**
 * 商城的controller
 */
@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/shop")
public class ShopController {

	@Autowired
	public ShopServiceImpl ssi;

	/**
	 * 在商城新增商品
	 * @param porderId
	 * @param memberId
	 */
	@PostMapping("addShop")
	public void addShop(Integer porderId, Integer memberId) {
		ssi.addShop(porderId, memberId);
	}

	/**
	 * 查詢總金額
	 * @param memberId
	 * @return
	 */
	@GetMapping("querySum")
	public Integer querySum(Integer memberId) {

		return ssi.querySum(memberId);
	}
}
