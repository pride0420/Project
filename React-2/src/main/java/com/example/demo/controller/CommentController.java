package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.Impl.CommentServiceImpl;
import com.example.demo.vo.Comment;

/**
 * 留言的controller
 */
@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	public CommentServiceImpl cmsi;

	/**
	 * 新增留言
	 * @param c
	 * @return
	 */
	@PostMapping("addComment")
	public String addComment(@RequestBody Comment c) {
		cmsi.addComment(c);
		return "add";
	}

	/**
	 * 查詢貼文底下的留言
	 * @param chatId
	 * @param memberId
	 * @return
	 */
	@GetMapping("queryAllComment")
	public List<Comment> queryAllComment(Integer chatId, Integer memberId) {
		return cmsi.queryAllComment(chatId, memberId);
	}
	
	/**
	 * 刪除留言
	 * @param commentId
	 * @return
	 */
	@PostMapping("deleteComment")
	public String deleteComment(Integer commentId) {
		cmsi.deleteComment(commentId);
		return "已刪除";
	}

}
