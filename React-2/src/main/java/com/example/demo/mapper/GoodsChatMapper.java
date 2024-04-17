package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.vo.Chat;

/**
 * 貼文按讚的Mapper
 */
@Mapper
public interface GoodsChatMapper {

	/**
	 * 新增按讚的貼文
	 * @param c
	 */
	@Insert("insert into goodchat(memberId,chatId) values(#{memberId},#{chatId})")
	void addGoodChat(Chat c);

	/**
	 * 查詢帳號按讚的全貼文
	 * @param memberId
	 * @return
	 */
	@Select("select * from test.goodchat where memberId=#{memberId}")
	List<Chat> queryGoodChatMId(Integer memberId);

	
	/**
	 * 查詢帳號跟貼文id符合的資料
	 * @param c
	 * @return
	 */
	@Select("select *from goodchat where memberId=#{memberId} and chatId=#{chatId}")
	Chat queryGoodId(Chat c);

	/**
	 * 統計貼文按讚數
	 * @param chatId
	 * @return
	 */
	@Select("select count(*) from goodchat where chatId=#{chatId}")
	Integer queryGoodsChatSum(Integer chatId);
	
	/**
	 * 取消按讚貼文
	 * @param id
	 */
	@Delete("delete from goodchat where id=#{id}")
	void deleteGoodChat(Integer id);

}
