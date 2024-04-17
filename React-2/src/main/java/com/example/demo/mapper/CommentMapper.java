package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.vo.Comment;

/**
 * 留言的Mapper
 */
@Mapper
public interface CommentMapper {

	/**
	 * 新增留言
	 * @param c
	 */
	@Insert("insert into comment(memberId,chatId,comment)" + "values(#{memberId},#{chatId},#{comment})")
	void addComment(Comment c);

	/**
	 * 查詢單則貼文底下的全部留言
	 * @param chatId
	 * @return
	 */
	@Select("select * from commentview where chatId=#{chatId} order by commentId desc")
	List<Comment> queryAllComment(Integer chatId);

	/**
	 * 查詢單則貼文的留言數
	 * @param chatId
	 * @return
	 */
	@Select("select count(*) from comment where chatId=#{chatId}")
	Integer queryCommentSum(Integer chatId);
	
	/**
	 * 刪除留言
	 * @param commentId
	 */
	@Delete("delete from test.comment where commentId=#{commentId}")
	void deleteComment(Integer commentId);

}
