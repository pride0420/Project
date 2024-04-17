package com.example.demo.vo;

import java.time.LocalDate;

/**
 * 帳戶的vo
 */
public class Member {

	/**鍵值*/
	private Integer memberId;
	
	/**帳號*/
	private String username;
	
	/**密碼*/
	private String password;
	
	/**暱稱*/
	private String name;
	
	/**信箱*/
	private String email;
	
	/**電話*/
	private String phone;
	
	private String newPassword;
	/**權限*/
	private String role;
	private boolean enabled = true;
    private boolean premium = false;
    private String accessToken;
    private LocalDate trailExpiration;

	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Member(String username, String password, String name, String email, String phone,String role) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.role=role;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isPremium() {
		return premium;
	}

	public void setPremium(boolean premium) {
		this.premium = premium;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public LocalDate getTrailExpiration() {
		return trailExpiration;
	}

	public void setTrailExpiration(LocalDate trailExpiration) {
		this.trailExpiration = trailExpiration;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
