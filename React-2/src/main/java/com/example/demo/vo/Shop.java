package com.example.demo.vo;

/**
 * 商城的vo
 */
public class Shop {

	/**鍵值*/
	private Integer id;
	
	/**member的id*/
	private Integer memberId;
	
	/**porder的id*/
	private Integer porderId;
	
	/**數量*/
	private Integer num;
	
	/**總金額*/
	private Integer sum;

	public Shop(Integer memberId, Integer porderId, Integer num, Integer sum) {
		super();
		this.memberId = memberId;
		this.porderId = porderId;
		this.num = num;
		this.sum = sum;
	}

	public Shop() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
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
