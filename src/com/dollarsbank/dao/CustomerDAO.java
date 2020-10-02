package com.dollarsbank.dao;
//import java.util.List;

import com.dollarsbank.model.Customer;

public interface CustomerDAO {
	
	public Customer getCustomerById(String userId);
	
	public boolean addCustomer(Customer customer);
	
	
}
