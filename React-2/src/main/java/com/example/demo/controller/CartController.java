package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.Impl.CartServiceImpl;
import com.example.demo.vo.Cart;

/**
 * 購物車的controller
 */
@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/car")
public class CartController {

	@Autowired
	public CartServiceImpl csi;

	/**
	 * 找出帳號的購物車內容
	 * @param memberId
	 * @return
	 */
	@GetMapping("queryCar")
	public List<Cart> queryCar(Integer memberId) {
		return csi.queryAll(memberId);
	}

	/**
	 * 在購物車畫面更新購物車內容
	 * @param id
	 * @param item
	 */
	@PostMapping("updateCar")
	public void updateCar(Integer id, String item) {
		csi.updateCar(id, item);
	}

	/**
	 * 清空購物車 並發信 結帳時使用
	 * @param memberId
	 * @param email
	 * @param c
	 * @param bindingResult
	 * @throws IOException
	 */
	@PostMapping("checkCar")
	public void checkCar(Integer memberId, String email,Integer sum, Cart c,BindingResult bindingResult) throws IOException {
		csi.checkCar(memberId);
		csi.sendRegistrationEmail(email,sum);

	}
}
