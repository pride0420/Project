package com.example.demo.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.CommentMapper;
import com.example.demo.mapper.GoodsCommentMapper;
import com.example.demo.service.GoodsCommentService;
import com.example.demo.vo.Comment;

/**
 * 留言的實作
 */
@Service
public class GoodsCommentServiceImpl implements GoodsCommentService {

	@Autowired
	private GoodsCommentMapper gcmp;

	/**
	 * 留言按讚或取消
	 */
	@Override
	public String addGoodComment(Comment c) {

		Integer x = gcmp.queryCommentId(c);
		if (x != null) {
			gcmp.deleteGoodComment(x);
			return "已取消";
		} else {
			gcmp.addGoodComment(c);
			return "已按讚";
		}
	}

}
