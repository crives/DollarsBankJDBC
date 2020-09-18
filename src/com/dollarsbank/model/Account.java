package com.dollarsbank.model;

public class Account {

	public String name;
	public String userId;
	public String password;
	public Long initialDeposit;
	public int balance;
	public int previousTransaction;

	public Account(String name, String userId, String password, Long initialDeposit, int balance,
			int previousTransaction) {
		super();
		this.name = name;
		this.userId = userId;
		this.password = password;
		this.initialDeposit = initialDeposit;
		this.balance = balance;
		this.previousTransaction = previousTransaction;
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

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getPreviousTransaction() {
		return previousTransaction;
	}

	public void setPreviousTransaction(int previousTransaction) {
		this.previousTransaction = previousTransaction;
	}

	public void deposit(int initialDeposit) {
		
		if(initialDeposit != 0) {
			balance = balance + initialDeposit;
			previousTransaction = -initialDeposit;
		}
	}
	
	public void withdraw(int amount) {
		
		if(amount != 0) {
			
			balance = balance - amount;
			
		}
	}

	@Override
	public String toString() {
		return "Account [name=" + name + ", userId=" + userId + ", password=" + password + ", initialDeposit="
				+ initialDeposit + ", balance=" + balance + ", previousTransaction=" + previousTransaction + "]";
	}
	
}