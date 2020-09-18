package com.dollarsbank.dao;
import java.util.List;

import com.dollarsbank.model.Customer;

public interface CustomerDAO {
	
	public List<Customer> getAllCustomers();
	
	public Customer getCustomerById(String userId);
	
//	public Customer getCustomer(String name, String address, String contactNumber);
	
	public boolean addCustomer(Customer customer);
	
	public boolean updateCustomer(Customer customer);
	
}
