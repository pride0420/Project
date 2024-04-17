package com.example.demo.vo;

import java.sql.Timestamp;

/**
 * 留言的vo
 */
public class Comment {

	/**鍵值*/
	private Integer commentId;
	
	/**member的id*/
	private Integer memberId;
	
	/**chat的id*/
	private Integer chatId;
	
	/**留言內容*/
	private String comment;
	
	/**留言時間*/
	private Timestamp firsttime;

	/**commentView的name*/
	private String name;
	
	/**更新按讚狀態*/
	private boolean item;
	
	/**用於顯示刪除按鈕*/
	private boolean button;
	
	/**記錄按讚數*/
	private Integer goodsCommentSum;

	public Comment(Integer memberId, Integer chatId, String comment) {
		super();
		this.memberId = memberId;
		this.chatId = chatId;
		this.comment = comment;
	}

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getGoodsCommentSum() {
		return goodsCommentSum;
	}

	public void setGoodsCommentSum(Integer goodsCommentSum) {
		this.goodsCommentSum = goodsCommentSum;
	}

	public boolean isButton() {
		return button;
	}

	public void setButton(boolean button) {
		this.button = button;
	}

	public boolean getItem() {
		return item;
	}

	public void setItem(boolean item) {
		this.item = item;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Integer getChatId() {
		return chatId;
	}

	public void setChatId(Integer chatId) {
		this.chatId = chatId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Timestamp getFirsttime() {
		return firsttime;
	}

	public void setFirsttime(Timestamp firsttime) {
		this.firsttime = firsttime;
	}

}
