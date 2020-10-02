package com.dollarsbank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dollarsbank.model.Customer;
import com.dollarsbank.connection.ConnectionManager;

public class CustomerDAOImp implements CustomerDAO {
	
	private Connection conn = ConnectionManager.getConnection();

	@Override
	public Customer getCustomerById(String userId) {
		
		Customer customer = null;
		
		try(PreparedStatement pstmt = conn.prepareStatement("select * from customer where user_id = ?")) {
			
			pstmt.setString(1, userId);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				String user_Id = rs.getString(1);
				String password = rs.getString(2);
				String name = rs.getString(3);
				String address = rs.getString(4);
				String contact_number = rs.getString(5);

				customer = new Customer(name, address, contact_number, user_Id, password);
			}
			
			pstmt.close();
		
		} catch(SQLException e) {
			
			e.printStackTrace();
		}
		
		return customer;
	}
	
	
	@Override
	public boolean addCustomer(Customer customer) {
		
//		if(getCustomer(customer.getName(), customer.getAddress(), customer.getContactNumber()) != null ) {
//			updateCustomer(getCustomer(customer.getName(), customer.getAddress(), customer.getContactNumber()));
//			return getCustomer(customer.getName(), customer.getAddress(), customer.getContactNumber());
//		} else {
			try {
				PreparedStatement pstmt = conn.prepareStatement("insert into customer values(?, ?, ?, ?, ?)");
				
				pstmt.setString(1, customer.getUserId());
				pstmt.setString(2, customer.getPassword());
				pstmt.setString(3, customer.getName());
				pstmt.setString(4, customer.getAddress());
				pstmt.setString(5, customer.getContactNumber());
				
				int insert = pstmt.executeUpdate();
				
				if(insert > 0) {
//					customer = getCustomer(customer.getName(), customer.getAddress(), customer.getContactNumber());
//					return customer;
					return true;
				}
			
				pstmt.close();
			
		} catch(SQLException e) {
			
			e.printStackTrace();
		}
			
//	}
//		return null;
		return false;
		
	}
	
//	@Override
//	public boolean updateCustomer(Customer customer) {
//
//		try(PreparedStatement pstmt = conn.prepareStatement("update customer set name = ?, address = ?, contact_number = ? where user_id = ?")) {
//
//			pstmt.setString(1, customer.getName());
//			pstmt.setString(2, customer.getAddress());
//			pstmt.setString(3, customer.getContactNumber());
//
//			int updated = pstmt.executeUpdate();
//
//			if(updated > 0) {
//				return true;
//			}
//			pstmt.close();
//			
//		} catch(SQLException e) {
//			
//			e.printStackTrace();
//		}
//		
//		return false;
//	}
}
