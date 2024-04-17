package com.example.demo.vo;

/**
 * 購物車的vo
 */
public class Cart {
	
	/**購物車的id*/
	private Integer id;
	
	/**商品的id*/
	private Integer porderId;
	
	/**商品名稱*/
	private String product;
	
	/**商品介紹*/
	private String mode;
	
	/**商品價格*/
	private Integer amount;
	
	/**商品數量*/
	private Integer num;
	
	/**總金額*/
	private Integer sum;

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getSum() {
		return sum;
	}

	public void setSum(Integer sum) {
		this.sum = sum;
	}

}
