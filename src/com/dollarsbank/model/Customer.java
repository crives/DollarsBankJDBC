package com.dollarsbank.model;

public class Customer {

	private String name;
	private String address;
	private String contactNumber;
	private String userId;
	private String password;
	
	public Customer(String name, String address, String contactNumber, String userId, String password) {
		super();
		this.name = name;
		this.address = address;
		this.contactNumber = contactNumber;
		this.userId = userId;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Customer [name=" + name + ", address=" + address + ", contactNumber=" + contactNumber
				+ ", userId=" + userId + ", password=" + password + "]";
	}	
	
}