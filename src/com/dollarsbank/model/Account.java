package com.dollarsbank.model;

public class Account {

	public String userId;
	public String accountNumber;
	public int balance;

	public Account() {}
	
	public Account(String accountNumber, String userId, int balance) {
		super();
		this.userId = userId;
		this.accountNumber = accountNumber;
		this.balance = balance;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [userId=" + userId + ", accountNumber=" + accountNumber + ", balance=" + balance + "]";
	}

//	public void deposit(int initialDeposit) {
//		
//		if(initialDeposit != 0) {
//			balance = balance + initialDeposit;
//			previousTransaction = -initialDeposit;
//		}
//	}
//	
//	public void withdraw(int amount) {
//		
//		if(amount != 0) {
//			
//			balance = balance - amount;
//			
//		}
//	}


}