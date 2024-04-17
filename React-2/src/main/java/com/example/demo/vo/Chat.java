package com.example.demo.vo;

import java.sql.Timestamp;

/**
 * 貼文的vo
 */
public class Chat {
	
	/**資料庫goodchat的id*/
	private Integer id;// 借goodchat
	
	/**鍵值*/
	private Integer chatId;
	
	/**member的id*/
	private Integer memberId;
	
	/**貼文內容*/
	private String content;
	
	/**貼文時間*/
	private Timestamp firsttime;
	
	
	public Chat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Chat(Integer memberId, String content) {
		super();
		this.memberId = memberId;
		this.content = content;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getChatId() {
		return chatId;
	}

	public void setChatId(Integer chatId) {
		this.chatId = chatId;
	}

	public Timestamp getFirsttime() {
		return firsttime;
	}

	public void setFirsttime(Timestamp firsttime) {
		this.firsttime = firsttime;
	}

	public Chat(String content) {
		super();
		this.content = content;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
