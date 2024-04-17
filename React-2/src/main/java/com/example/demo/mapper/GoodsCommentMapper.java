package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.vo.Comment;

/**
 * 留言按讚的Mapper
 */
@Mapper
public interface GoodsCommentMapper {

	/**
	 * 新增按讚的留言
	 * @param c
	 */
	@Insert("insert into goodcomment(memberId,chatId,commentId)" + "values(#{memberId},#{chatId},#{commentId})")
	void addGoodComment(Comment c);

	/**
	 * 查詢帳號按讚的全部留言
	 * @param memberId
	 * @param chatId
	 * @return
	 */
	@Select("select * from goodcomment where memberId=#{memberId} and chatId=#{chatId}")
	List<Comment> queryGoodComment(Integer memberId, Integer chatId);

	/**
	 * 查詢帳號跟留言id符合的資料
	 * @param c
	 * @return
	 */
	@Select("select id from goodcomment where memberId=#{memberId} and commentId=#{commentId}")
	Integer queryCommentId(Comment c);

	/**
	 * 統計留言按讚數
	 * @param chatId
	 * @return
	 */
	@Select("select count(*) from goodcomment where commentId=#{commentId}")
	Integer queryGoodsCommentSum(Integer commentId);
	
	/**
	 * 取消按讚留言
	 * @param id
	 */
	@Delete("delete from goodcomment where id=#{id}")
	void deleteGoodComment(Integer id);
}
