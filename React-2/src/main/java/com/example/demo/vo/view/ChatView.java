package com.example.demo.vo.view;

import java.sql.Timestamp;

/**
 * 留言的資料庫view
 */
public class ChatView {
	/**chat的id*/
	private Integer ChatId;
	
	/**member的id*/
	private Integer memberId;
	
	/**member的name*/
	private String name;
	
	/**chat的貼文內容*/
	private String content;
	
	/**chat的貼文發佈時間*/
	private Timestamp firsttime;
	
	/**用於更新chat按讚狀態*/
	private boolean item;
	
	/**用於跟新chat底下comment的數量*/
	private Integer chatSum;
	
	/**記錄按讚數*/
	private Integer goodsChatSum;


	public ChatView() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getGoodsChatSum() {
		return goodsChatSum;
	}

	public void setGoodsChatSum(Integer goodsChatSum) {
		this.goodsChatSum = goodsChatSum;
	}
	
	public Integer getChatSum() {
		return chatSum;
	}

	public void setChatSum(Integer chatSum) {
		this.chatSum = chatSum;
	}

	public boolean getItem() {
		return item;
	}

	public void setItem(boolean item) {
		this.item = item;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getChatId() {
		return ChatId;
	}

	public void setChatId(Integer chatId) {
		ChatId = chatId;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getFirsttime() {
		return firsttime;
	}

	public void setFirsttime(Timestamp firsttime) {
		this.firsttime = firsttime;
	}

}
