package com.example.demo.service;

import java.util.List;

import com.example.demo.vo.Cart;

/**
 * 購物車的service
 */
public interface CartService {

	List<Cart> queryAll(Integer memberId);

	void updateCar(Integer id, String item);

	void checkCar(Integer memberId);
}
