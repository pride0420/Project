package com.example.demo.service;

import java.util.List;

import com.example.demo.vo.Shop;

/**
 * 商城的service
 */
public interface ShopService {

	void addShop(Integer porderId, Integer memberId);

	Integer querySum(Integer memberId);

}
