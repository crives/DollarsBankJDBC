package com.dollarsbank.dao;

import java.util.List;

import com.dollarsbank.model.Transaction;

public interface TransactionDAO {

	public List<Transaction> getTransactionsByIds(String userId, String accountId);
	
	public boolean addTransaction(Transaction transaction);
	
	
}
