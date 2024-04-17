package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.vo.Chat;
import com.example.demo.vo.view.ChatView;

/**
 * 貼文的Mapper
 */
@Mapper
public interface ChatMapper {

	/**
	 * 新增貼文
	 * @param c
	 */
	@Insert("insert into chat(memberId,content) values(#{memberId},#{content})")
	void addChat(Chat c);

	/**
	 * 用id查詢貼文
	 * @param chatId
	 * @return
	 */
	@Select("select * from chat where chatId=#{chatId}")
	Chat queryId(Integer chatId);

	/**
	 * 查詢全部貼文完整內容 新的在前面
	 * @return
	 */
	@Select("select * from chatview order by chatId desc")
	List<ChatView> queryAllChat();

	/**
	 * 查詢單個貼文完整內容
	 * @param chatId
	 * @return
	 */
	@Select("select * from chatview where chatId=#{chatId}")
	ChatView queryChat(Integer chatId);

	/**
	 * 查詢帳號發的全部貼文
	 * @param memberId
	 * @return
	 */
	@Select("select * from chatview where memberId=#{memberId} order by chatId desc")
	List<ChatView> queryUserChat(Integer memberId);
	
	
	/**
	 * 更新貼文
	 * @param c
	 */
	@Update("update chat set content=#{content} where chatId=#{chatId}")
	void updateChat(Chat c);

	
	/**
	 * 刪除貼文
	 * @param chatId
	 */
	@Delete("delete from chat where chatId=#{chatId}")
	void deleteChat(Integer chatId);
}
