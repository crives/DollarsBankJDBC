package com.dollarsbank.controller;

import java.util.Scanner;

import com.dollarsbank.model.Account;
import com.dollarsbank.model.Customer;
import com.dollarsbank.dao.AccountDAO;
import com.dollarsbank.dao.AccountDAOImp;
import com.dollarsbank.dao.CustomerDAO;
import com.dollarsbank.dao.CustomerDAOImp;

public class DollarsBankController {
	

	public static final CustomerDAO customerdao = new CustomerDAOImp();
	public static final AccountDAO accountdao = new AccountDAOImp();
	
	
	public static void main(String args[]) {
		welcome();
	}
	
	public static void welcome() {
		int choice = 0;

//		boolean invalidInput;
		final Scanner sc = new Scanner(System.in);

			do {
			System.out.println("---- Welcome to Dollars Bank! ----");
			System.out.println("\n1. Create New Account");
			System.out.println("\n2. Login");
			System.out.println("\n3. Exit.");
			System.out.println("\nEnter Choice (1, 2, or 3) :");
			
			choice = Integer.parseInt(sc.nextLine().trim());
			
			} while(choice > 3 || choice < 0 && choice != 3);
			
			switch(choice) {
			case 1:
				createAccount();
				break;
			case 2: 
				login();
				break;
			case 3: 
				System.out.println("Goodbye.");	
				break;
		}
		
	}
	

	String customerName;
	String customerId;
	
	static Scanner sc = new Scanner(System.in);
	
	
	static void login() {
		System.out.println("Login");
		System.out.println("\nEnter UserId: ");
		String userId = sc.nextLine().trim();
//		if .getAccountByUserId(userId);
		
		Customer found = customerdao.getCustomerById(userId);
		if(found != null) {
			
			System.out.println("\nEnter Password: ");
			String password = sc.nextLine().trim();
			
			if(password.equals(found.getPassword())) {
				System.out.println("Login Successful!");
				afterLogin(found);
			}
			
		}		
	}
	
	public static void createAccount() {
		
		String name;
		String userId;
		String password;
		int initialDeposit;
		String address = null;
		String contactNumber = null;
		
		System.out.println("Create a New Account");
		System.out.println("Enter your name.");
		name = sc.nextLine().trim();
		
		System.out.println("\nCreate a UserId: ");
		userId = sc.nextLine().trim();
		
		System.out.println("\nCreate a password (8 characters with lower, upper, and special): ");
		password = sc.nextLine().trim();
		
		System.out.println("\nEnter an initial deposit amount: ");
		initialDeposit = sc.nextInt();
		
		
		Customer customer = new Customer(name, address, contactNumber, userId, password);
		boolean added = customerdao.addCustomer(customer);
		
		if(added) {
			Account account = new Account();
			account.deposit(initialDeposit);
			account.setUserId(userId);
			boolean created = accountdao.addAccount(account);
			if(created) {	
				System.out.println("Account Created! \nPlease login to continue!");
				login();
			
				} else {
			
					System.out.println("Invalid input!");
				}
			} else {
				System.out.println("Invalid input!");
		}
		
	}
		
	public static void afterLogin(Customer customer) {
		
		int choice;
		
		System.out.println("Welcome to Dollars Bank!! \nChoose from the menu options below.");
		Scanner sc1 = new Scanner(System.in);
		boolean valid = true;
		
		while(valid) {
			System.out.println("1. Create an Account");
			System.out.println("2. Make a Deposit");
			System.out.println("3. Make a Withdrawal");
//			System.out.println("4. Funds Transfer");
			System.out.println("4. Transaction History");
			System.out.println("5. Display Customer Information");
			System.out.println("6. Sign Out");
			System.out.println("Enter Choice 1 - 6");
			
			try {
				choice = sc1.nextInt();
				switch(choice) {
				case 1: 
					customerDeposit();
					break;
				case 2: 
					customerWithdrawal();
					break;
					
				}
				
			} catch(IllegalArgumentException e) {
				
				System.out.println("Invalid Choice!");
			}
			
		}
		
	}
	
	static void customerWithdrawal() {
		
		
	}
	
	static void customerDeposit() {
		System.out.println("How much would you like to deposit?");
		int amount = Integer.parseInt(sc.nextLine());
		Account acct = null;
		
		acct.deposit(amount);
		boolean updated = accountdao.updateAccount(acct);
		
		if(updated) {
			
			System.out.println("Deposit successful!");
			System.out.println("Your new balance is: " + acct.getBalance());
		}
		
	}
	
	void getPreviousTransaction() {
		
		int previousTransaction = 0;

		if(previousTransaction > 0) {

			System.out.println("Deposited: " + previousTransaction);
		
		} else if (previousTransaction < 0) {
			
			System.out.println("Withdrawn: " + Math.abs(previousTransaction));
		
		} else {
			
			System.out.println("No transaction occured.");
			
		}
			
		}
	}
