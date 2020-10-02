package com.dollarsbank.model;

public class Transaction {

	public int transId;
	public String type;
	public int transAmount;
	public String userId;
	public String accountId;
	
	public Transaction(int transId, String type, int transAmount, String userId, String accountId) {
		super();
		this.transId = transId;
		this.type = type;
		this.transAmount = transAmount;
		this.userId = userId;
		this.accountId = accountId;
	}

	public int getTransId() {
		return transId;
	}

	public void setTransId(int transId) {
		this.transId = transId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getTransAmount() {
		return transAmount;
	}

	public void setTransAmount(int transAmount) {
		this.transAmount = transAmount;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	@Override
	public String toString() {
		return "Transaction [transId=" + transId + ", type=" + type + ", transAmount=" + transAmount + ", userId="
				+ userId + ", accountId=" + accountId + "]";
	}
	
}
