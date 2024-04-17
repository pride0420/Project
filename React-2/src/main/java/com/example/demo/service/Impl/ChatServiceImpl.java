package com.example.demo.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.ChatMapper;
import com.example.demo.mapper.CommentMapper;
import com.example.demo.mapper.GoodsChatMapper;
import com.example.demo.service.ChatService;
import com.example.demo.vo.Chat;
import com.example.demo.vo.view.ChatView;

/**
 * 貼文的實作
 */
@Service
public class ChatServiceImpl implements ChatService {

	@Autowired
	private ChatMapper cm;

	@Autowired
	private GoodsChatMapper gchm;

	@Autowired
	private CommentMapper ctm;

	/**
	 * 新增貼文
	 */
	@Override
	public void addChat(Chat c) {
		cm.addChat(c);
	}
	
	/**
	 * 查詢全部貼文
	 */
	@Override
	public List<ChatView> queryAllChat(Integer memberId) {
		List<ChatView> l = queryGood(memberId);
		return l;
	}

	/**
	 * 查詢帳號有按讚的貼文 並統計按讚數
	 * 統計貼文底下的留言數
	 * queryAllChat使用
	 * @param memberId
	 * @return
	 */
	public List<ChatView> queryGood(Integer memberId) {
		List<ChatView> l = cm.queryAllChat();
		List<Chat> g = gchm.queryGoodChatMId(memberId);
		for (ChatView o : l) {
			o.setItem(false);
		}
		for (ChatView o : l) {
			o.setChatSum(ctm.queryCommentSum(o.getChatId()));
			o.setGoodsChatSum(gchm.queryGoodsChatSum(o.getChatId()));
			for (Chat p : g) {
				if (p.getChatId() == o.getChatId()) {
					o.setItem(true);
					
				}
			}
		}
		return l;
	}

	/**
	 * 用id找出該則貼文
	 */
	@Override
	public ChatView queryChat(Integer chatId) {
		ChatView c = cm.queryChat(chatId);
		return c;
	}
	
	/**
	 * 用找出該帳號的貼文
	 */
	@Override
	public List<ChatView> queryUserChat(Integer memberId) {
		List<ChatView> l = cm.queryUserChat(memberId);
		return l;
	}

	/**
	 * 刪除貼文
	 */
	@Override
	public void deleteChat(Integer chatId) {
		Chat c = cm.queryId(chatId);
		cm.deleteChat(c.getChatId());
	}
	
	/**
	 * 更新貼文內容
	 */
	@Override
	public void updateChat(Chat c) {
		Chat ch=cm.queryId(c.getChatId());
		ch.setContent(c.getContent());
		cm.updateChat(ch);
	}

}
