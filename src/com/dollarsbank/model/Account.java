package com.dollarsbank.model;

public class Account {

	public String name;
	public String userId;
	public String password;
	public Long initialDeposit;
	
	public Account(String name, String userId, String password, Long initialDeposit) {
		super();
		this.name = name;
		this.userId = userId;
		this.password = password;
		this.initialDeposit = initialDeposit;
	}
	
	public Account() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getInitialDeposit() {
		return initialDeposit;
	}

	public void setInitialDeposit(Long initialDeposit) {
		this.initialDeposit = initialDeposit;
	}

	@Override
	public String toString() {
		return "Account [name=" + name + ", userId=" + userId + ", password=" + password + ", initialDeposit="
				+ initialDeposit + "]";
	}
	
}