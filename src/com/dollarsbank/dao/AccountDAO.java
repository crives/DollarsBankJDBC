package com.dollarsbank.dao;
import java.util.List;

import com.dollarsbank.model.Account;

public interface AccountDAO {
	
	public List<Account> getAllAccounts();
	
	public Account getAccountByUserId(String userId);
		
	public Account getAccount(String name, String userId, String password, Long initialDeposit);
	
	public boolean addAccount(Account account);
	
	public boolean updateAccount(Account account);

	
}
