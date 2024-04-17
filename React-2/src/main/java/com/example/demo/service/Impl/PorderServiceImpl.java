package com.example.demo.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.PorderMapper;
import com.example.demo.service.PorderService;
import com.example.demo.vo.Porder;

/**
 * 商品的實作
 */
@Service
public class PorderServiceImpl implements PorderService {

	@Autowired
	private PorderMapper pm;

	/**
	 * 用id找出商品內容 Mapper找出四筆 用於一開始進入商城的畫面
	 */
	@Override
	public List<Porder> queryId() {
		List<Porder> l = pm.queryId();
		return l;
	}

	/**
	 * 用item找出商品 用於商城的分類顯示
	 */
	@Override
	public List<Porder> queryItems(String items) {
		List<Porder> l = pm.queryItems(items);
		return l;
	}


}
