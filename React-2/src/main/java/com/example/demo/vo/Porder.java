package com.example.demo.vo;

/**
 * 商品的vo
 */
public class Porder {

	/**鍵值*/
	private Integer porderId;
	
	/**商品名稱*/
	private String product;
	
	/**定價*/
	private Integer amount;
	
	/**圖片名*/
	private String imgNo;
	
	/**介紹*/
	private String mode;
	
	/**說明*/
	private String explanation;
	
	/**類別*/
	private String items;

	public Porder() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getExplanation() {
		return explanation;
	}


	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}


	public Integer getPorderId() {
		return porderId;
	}

	public void setPorderId(Integer porderId) {
		this.porderId = porderId;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getImgNo() {
		return imgNo;
	}

	public void setImgNo(String imgNo) {
		this.imgNo = imgNo;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

}
