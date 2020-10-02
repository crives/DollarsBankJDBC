package com.dollarsbank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dollarsbank.model.Account;
import com.dollarsbank.connection.ConnectionManager;

public class AccountDAOImp implements AccountDAO {
	
	private Connection conn = ConnectionManager.getConnection();
	
	@Override
	public List<Account> getAccountByUserId(String userId) {
		
		List<Account> account = new ArrayList<Account>();
		
		// select * from account where user_id = ?
		try(PreparedStatement pstmt = conn.prepareStatement("select * from account where user_id = ?")) {
			
			pstmt.setString(1, userId);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next() ) {
				String user_id = rs.getString(1);
				String acct_id = rs.getString(2);
				int balance = rs.getInt(3);
				
				account.add(new Account(user_id, acct_id, balance));
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return account;
	}
	
	@Override
	public Account getAccountById(String accountId) {
		
		Account account = null;
		
		// select * from account where user_id = ?
		try(PreparedStatement pstmt = conn.prepareStatement("select * from account where account_id = ?")) {
			
			pstmt.setString(1, accountId);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next() ) {
				String user_id = rs.getString(1);
				String acct_id = rs.getString(2);
				int balance = rs.getInt(3);
				
				account = new Account(user_id, acct_id, balance);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
			return account;
	}
	
	@Override
	public boolean addAccount(Account account) {
		
//		if(getAccount(account.getName(), account.getUserId(), account.getPassword(), account.getInitialDeposit()) != null) {
//			updateAccount(getAccount(account.getName(), account.getUserId(), account.getPassword(), account.getInitialDeposit()));
//			return getAccount(account.getName(), account.getUserId(), account.getPassword(), account.getInitialDeposit());
		
//		} else {
			try {
				PreparedStatement pstmt = conn.prepareStatement("insert into account values(?, ?, ?)");
				
				pstmt.setString(1, account.getUserId());
				pstmt.setString(2, account.getAccountNumber());
				pstmt.setInt(3, account.getBalance());
				int insert = pstmt.executeUpdate();
				
				if(insert > 0) {
//					account = getAccount(account.getName(), account.getUserId(), account.getPassword(), account.getInitialDeposit());
//					return account;
					return true;
				}
				
				pstmt.close();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
//		}
		return false;
	}
	
	@Override
	public boolean updateAccount(Account account) {
		
		try(PreparedStatement pstmt = conn.prepareStatement("update account set user_id = ?, accountNumber = ?, balance =?")) {
			
			pstmt.setString(1, account.getUserId());
			pstmt.setString(2, account.getAccountNumber());
			pstmt.setLong(3, account.getBalance());
			
			int updated = pstmt.executeUpdate();
			
			if(updated > 0) {
				return true;
			}
			pstmt.close();
			
		} catch(SQLException e) {
			
			e.printStackTrace();
		}
		
		return false;
	}

}
