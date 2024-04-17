package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.Impl.GoodsCommentServiceImpl;
import com.example.demo.vo.Comment;

/**
 * 留言按讚的controller
 */
@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("goodComment")
public class GoodsCommentController {

	@Autowired
	public GoodsCommentServiceImpl gi;

	/**
	 * 留言按讚或取消
	 * @param c
	 * @return
	 */
	@PostMapping("addGoodComment")
	public String addGoodComment(@RequestBody Comment c) {
		return gi.addGoodComment(c);
	}
}
