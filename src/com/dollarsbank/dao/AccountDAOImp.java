package com.dollarsbank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dollarsbank.model.Account;
import com.dollarsbank.connection.ConnectionManager;

public abstract class AccountDAOImp implements AccountDAO {
	
	private Connection conn = ConnectionManager.getConnection();

	
	public List<Account> getAllAccounts() {
		List<Account> accountList = new ArrayList<Account>();
		
		try(Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("select * from account");
			
			while(rs.next()) {
				
//				accountList.add(new Account(rs.getInt(1)))
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return accountList;
	}
	
	@Override
	public Account getAccountByUserId(String userId) {
		
		Account account = null;
		
		// select * from account where user_id = ?
		try(PreparedStatement pstmt = conn.prepareStatement("select * from account where user_id = ?")) {
			
			pstmt.setString(1, userId);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next() ) {
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return account;
	}
	
	@Override
	public Account getAccount(String name, String userId, String password, Long initialDeposit) {
		
		Account account = null;
		
		try(PreparedStatement pstmt = conn.prepareStatement("select * from account where user_id = ? and password = ?")) {
			
			pstmt.setString(1,  name);
			pstmt.setString(2,  userId);
			pstmt.setString(3,  password);
			pstmt.setLong(4, initialDeposit);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				account = new Account(rs.getString(1), rs.getString(2), rs.getString(3), rs.getLong(4));
						
			}
			
			pstmt.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return account;
	}
	
	@Override
	public Account addAccount(Account account) {
		
		if(getAccount(account.getName(), account.getUserId(), account.getPassword(), account.getInitialDeposit()) != null) {
			updateAccount(getAccount(account.getName(), account.getUserId(), account.getPassword(), account.getInitialDeposit()));
			return getAccount(account.getName(), account.getUserId(), account.getPassword(), account.getInitialDeposit());
		
		} else {
			try {
				PreparedStatement pstmt = conn.prepareStatement("insert into account values(?, ?, ?, ?)");
				
				pstmt.setString(1, account.getName());
				pstmt.setString(2, account.getUserId());
				pstmt.setString(3, account.getPassword());
				pstmt.setLong(4, account.getInitialDeposit());
				
				int insert = pstmt.executeUpdate();
				
				if(insert > 0) {
					account = getAccount(account.getName(), account.getUserId(), account.getPassword(), account.getInitialDeposit());
					return account;
				}
				
				pstmt.close();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
		
		return null;
	}
	
	
	@Override
	public boolean updateAccount(Account account) {
		
		try(PreparedStatement pstmt = conn.prepareStatement("update account set name = ?, user_id = ?, password = ?, initial_deposit =?")) {
			
			pstmt.setString(1, account.getName());
			pstmt.setString(2, account.getUserId());
			pstmt.setString(3, account.getPassword());
			pstmt.setLong(4, account.getInitialDeposit());
			
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
