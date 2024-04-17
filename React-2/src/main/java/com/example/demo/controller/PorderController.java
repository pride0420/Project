package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.Impl.PorderServiceImpl;
import com.example.demo.vo.Porder;

/**
 * 商品的controller
 */
@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/porder")
public class PorderController {

	@Autowired
	public PorderServiceImpl psi;

	/**
	 * 找出商城首頁顯示的商品
	 * @return
	 */
	@GetMapping("/queryId")
	public List<Porder> queryId() {
		return psi.queryId();
	}

	/**
	 * 根據分類找出商品
	 * @param items
	 * @return
	 */
	@GetMapping("queryItems")
	public List<Porder> queryItems(String items) {
		return psi.queryItems(items);
	}
	
}
