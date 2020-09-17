package com.dollarsbank.dao;
import java.util.List;

import com.dollarsbank.model.Customer;

public interface CustomerDAO {
	
	public List<Customer> getAllCustomers();
	
	public Customer getCustomerByName(String name);
	
	public Customer getCustomer(String name, String address, String contactNumber);
	
	public Customer addCustomer(Customer customer);
	
	public boolean updateCustomer(Customer customer);
	
}
