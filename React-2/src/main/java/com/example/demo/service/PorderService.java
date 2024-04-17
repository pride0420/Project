package com.example.demo.service;

import java.util.List;

import com.example.demo.vo.Porder;

/**
 * 商品的service
 */
public interface PorderService {

	List<Porder> queryId();

	List<Porder> queryItems(String items);
	

}
