package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.Impl.GoodsChatServiceImpl;
import com.example.demo.vo.Chat;

/**
 * 貼文按讚的controller
 */
@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/goodchat")
public class GoodsChatController {

	@Autowired
	public GoodsChatServiceImpl gchsi;
	
	/**
	 * 按讚或取消
	 * @param c
	 * @return
	 */
	@PostMapping("addGoodChat")
	public String addGoodChat(@RequestBody Chat c) {
		return gchsi.addGoodChat(c);
	}

}
