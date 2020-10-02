package com.dollarsbank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dollarsbank.connection.ConnectionManager;
import com.dollarsbank.model.Transaction;

public class TransactionDAOImp implements TransactionDAO {
	
	private Connection conn = ConnectionManager.getConnection();

	@Override
	public List<Transaction> getTransactionsByIds(String userId, String accountId) {
		
		List<Transaction> transaction = new ArrayList<Transaction>();
		
		// select * from account where user_id = ?
		try(PreparedStatement pstmt = conn.prepareStatement("select * from account where user_id = ?")) {
			
			pstmt.setString(1, userId);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next() ) {
				int trans_id = rs.getInt(1);
				int trans_amt = rs.getInt(2);
				String type = rs.getString(3);
				String user_id = rs.getString(4);
				String acct_id = rs.getString(5);
				
				transaction.add(new Transaction(trans_id, type, trans_amt, user_id, acct_id));
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return transaction;
	}

	@Override
	public boolean addTransaction(Transaction transaction) {
		
		try {
			PreparedStatement pstmt = conn.prepareStatement("insert into account values(?, ?, ?, ?, ?)");
			
			pstmt.setInt(1, transaction.getTransId());
			pstmt.setString(2, transaction.getType());
			pstmt.setInt(3, transaction.getTransAmount());
			pstmt.setString(4, transaction.getUserId());
			pstmt.setString(5,  transaction.getAccountId());
			
			int insert = pstmt.executeUpdate();
			
			if(insert > 0) {
//				account = getAccount(account.getName(), account.getUserId(), account.getPassword(), account.getInitialDeposit());
//				return account;
				return true;
			}
			
			pstmt.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
//	}
	return false;
	}
	

}
