package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.Impl.ChatServiceImpl;
import com.example.demo.service.Impl.GoodsChatServiceImpl;
import com.example.demo.service.Impl.MemberServiceImpl;
import com.example.demo.vo.Chat;
import com.example.demo.vo.Member;
import com.example.demo.vo.view.ChatView;

/**
 * 貼文的controller
 */
@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/chat")
public class ChatController {
	private final static Logger log = LoggerFactory.getLogger(Chat.class);
	@Autowired
	public ChatServiceImpl csi;

	@Autowired
	public GoodsChatServiceImpl gchsi;

	@Autowired
	public MemberServiceImpl ms;

	/**
	 * 新增貼文
	 * @param c
	 */
	
	@PostMapping("/addChat")
	public void addChat(@RequestBody Chat c) {
		csi.addChat(c);
	}
	
	
	/**
	 * 查詢全部貼文
	 * @param memberId
	 * @return
	 */
	
	@GetMapping("/queryAll")
	public List<ChatView> queryAllChat(Integer memberId) {
		return csi.queryAllChat(memberId);
	}
	
	/*
	@GetMapping("queryAll")
	public ResponseEntity<List<ChatView>> queryAllMember(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Member m=ms.queryUserTest(authentication.getName());
		    List<ChatView> c = csi.queryAllChat(m.getMemberId());
		    System.out.print(c);
	        return ResponseEntity.ok(c);  
	    }
	*/
	/**
	 * 用id查詢該則貼文
	 * @param chatId
	 * @return
	 */
	@GetMapping("/queryChat")
	public ChatView queryChat(Integer chatId) {
		return csi.queryChat(chatId);
	}

	/**
	 * 找出帳號發的貼文
	 * @param memberId
	 * @return
	 */
	@GetMapping("queryUserChat")
	public List<ChatView> queryUserChat(Integer memberId) {

		return csi.queryUserChat(memberId);
	}

	/**
	 * 刪除貼文
	 * @param chatId
	 * @return
	 */
	@PostMapping("deleteChat")
	public String deleteChat(Integer chatId) {
		csi.deleteChat(chatId);
		return "刪除成功";
	}
	
	/**
	 * 更新貼文內容
	 * @param c
	 */
	@PostMapping("updateChat")
	public void updateChat(@RequestBody Chat c) {
		csi.updateChat(c);
	}
}
