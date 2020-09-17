package com.dollarsbank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dollarsbank.model.Customer;
import com.dollarsbank.connection.ConnectionManager;

public abstract class CustomerDAOImp implements CustomerDAO {
	
	private Connection conn = ConnectionManager.getConnection();
//	private AccountDAOImp accDAO = new AccountDAOImp();
	
	@Override
	public List<Customer> getAllCustomers() {
		
		List<Customer> customerList = new ArrayList<Customer>();
		
		try(Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("select * from customer");
			
			while(rs.next()) {
				
//				customerList.add(new Customer(rs.get))
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return customerList;
	}
	
	@Override
	public Customer getCustomerByName(String name) {
		
		Customer customer = null;
		
		try(PreparedStatement pstmt = conn.prepareStatement("select * from customer where name = ?")) {
			
			pstmt.setString(1, name);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
//				customer = new Customer(rs.get)
			}
			
			pstmt.close();
		
		} catch(SQLException e) {
			
			e.printStackTrace();
		}
		
		return customer;
	}

	
	@Override
	public Customer getCustomer(String name, String address, String contactNumber) {
		
		Customer customer = null;
		
		try(PreparedStatement pstmt = conn.prepareStatement("select * from customer where name = ?, address = ? and contact_number = ?")) {
			
			pstmt.setString(1, name);
			pstmt.setString(2, address);
			pstmt.setString(3, contactNumber);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				customer = new Customer(rs.getString(1), rs.getString(2), rs.getString(3));
			
			}
			
			pstmt.close();
			
		} catch(SQLException e) {
			
			e.printStackTrace();
		}
		
		return customer;
	}
	
	
	@Override
	public Customer addCustomer(Customer customer) {
		
		if(getCustomer(customer.getName(), customer.getAddress(), customer.getContactNumber()) != null ) {
			updateCustomer(getCustomer(customer.getName(), customer.getAddress(), customer.getContactNumber()));
			return getCustomer(customer.getName(), customer.getAddress(), customer.getContactNumber());
		} else {
			try {
				PreparedStatement pstmt = conn.prepareStatement("insert into customer values(?, ?, ?)");
				
				pstmt.setString(1, customer.getName());
				pstmt.setString(2, customer.getAddress());
				pstmt.setString(3, customer.getContactNumber());
				
				int insert = pstmt.executeUpdate();
				
				if(insert > 0) {
					customer = getCustomer(customer.getName(), customer.getAddress(), customer.getContactNumber());
					return customer;
				}
			
				pstmt.close();
			
		} catch(SQLException e) {
			
			e.printStackTrace();
		}
			
		}
		return null;
		
	}
	
	@Override
	public boolean updateCustomer(Customer customer) {

		try(PreparedStatement pstmt = conn.prepareStatement("update customer set name = ?, address = ?, contact_number = ? where user_id = ?")) {

			pstmt.setString(1, customer.getName());
			pstmt.setString(2, customer.getAddress());
			pstmt.setString(3, customer.getContactNumber());

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
