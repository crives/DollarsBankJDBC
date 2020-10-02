package com.dollarsbank.dao;
import java.util.List;

import com.dollarsbank.model.Account;

public interface AccountDAO {
		
	public List<Account> getAccountByUserId(String userId);
	
	public Account getAccountById(String accountId);
			
	public boolean addAccount(Account account);
	
	public boolean updateAccount(Account account);

	
}
