package com.dollarsbank.controller;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.dollarsbank.model.Account;
import com.dollarsbank.model.Customer;
import com.dollarsbank.model.Transaction;
import com.dollarsbank.dao.AccountDAO;
import com.dollarsbank.dao.AccountDAOImp;
import com.dollarsbank.dao.CustomerDAO;
import com.dollarsbank.dao.CustomerDAOImp;
import com.dollarsbank.dao.TransactionDAO;
import com.dollarsbank.dao.TransactionDAOImp;

public class DollarsBankController {
	
	private static Customer currentCustomer = null;
	
	private static final TransactionDAO transactiondao = new TransactionDAOImp();
	private static final CustomerDAO customerdao = new CustomerDAOImp();
	private static final AccountDAO accountdao = new AccountDAOImp();
	
	private static Scanner sc = new Scanner(System.in);

	
	public static void main(String args[]) {
		welcome();
	}
	
	public static void welcome() {
		
		int choice = 0;

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
	
	
	static void login() {
		System.out.println("Login");
		String userId;
		
			System.out.println("\nEnter UserId: ");
			userId = sc.nextLine().trim();

			currentCustomer = customerdao.getCustomerById(userId);

			if(currentCustomer != null) {

				System.out.println("\nEnter Password: ");
				String password = sc.nextLine().trim();

				if(password.equals(currentCustomer.getPassword())) {
					System.out.println("Login Successful!");
					afterLogin();
				}	
			} 

			System.out.println("UserId was not found!");
		
	}
	
	public static void createAccount() {
		
		String name;
		String userId;
		String password;
		int initialDeposit;
		String address = null;
		String contactNumber = null;
		Integer accountNumber;
		Random r = new Random();
		
		System.out.println("Create a New Account");
		System.out.println("Enter your name.");
		name = sc.nextLine().trim();
		
		System.out.println("\nCreate a UserId: ");
		userId = sc.nextLine();
		
		System.out.println("\nCreate a password (8 characters with lower, upper, and special): ");
		password = sc.nextLine().trim();
		
		System.out.println("Enter your address: ");
		address = sc.nextLine().trim();
		
		System.out.println("Enter your phone number: ");
		contactNumber = sc.nextLine().trim();
		
		System.out.println("\nEnter an initial deposit amount: ");
		initialDeposit = Integer.parseInt(sc.nextLine().trim());
				
		do {
			
		accountNumber = r.nextInt((10000000 - 100000) + 1) + 100000;
		
		} while (accountdao.getAccountById(accountNumber.toString()) != null);
			
		
		System.out.println("\nYour account number is: " + accountNumber);

			
		currentCustomer = new Customer(name, address, contactNumber, userId, password);
		boolean added = customerdao.addCustomer(currentCustomer);
		
		if(added) {
			Account account = new Account(userId, accountNumber.toString(), initialDeposit);
			
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
		
	public static void afterLogin() {
		
		int choice = 0;
		
		System.out.println("Welcome to Dollars Bank!! \nChoose from the menu options below.");
		boolean valid, loggedIn;
		
		do {
			do {
				valid = false;
				System.out.println("1. Make a Deposit");
				System.out.println("2. Make a Withdrawal");
				// System.out.println("4. Funds Transfer");
				System.out.println("3. Transaction History");
				System.out.println("4. Display Customer Information");
				System.out.println("5. Sign Out");
				System.out.println("Enter Choice 1 - 5");

				try {
					choice = Integer.parseInt(sc.nextLine().trim());
					if(choice < 1 || choice > 5) {
						throw new IllegalArgumentException();
					}
				} catch(IllegalArgumentException e) {

					System.out.println("Invalid Choice!");
					valid = true;
				}

			} while(valid);
			
			loggedIn = true;
			switch(choice) {
			case 1: 
				customerDeposit();
				break;
			case 2: 
				customerWithdrawal();
				break;
			case 3:
				viewTransactions();
				break;
			case 4:
				displayCustomerInfo();
				break;
			case 5:
				currentCustomer = null;
				loggedIn = false;
				break;
			}	
		} while(loggedIn);
	}
	
	private static void displayCustomerInfo() {
		
		System.out.println(currentCustomer);
	}

	private static void viewTransactions() {
		
		String acct_num;
		
		do {
			System.out.println("Which account would you like to view transactions from? (Enter your acct #)");
			acct_num = sc.nextLine().trim();
			
				if(accountdao.getAccountById(acct_num) == null) {
				
					System.out.println("Incorrect Account Number!");
				}
			}
			
			while(accountdao.getAccountById(acct_num) == null);	
				
				
		
		
		if (transactiondao.getTransactionsByIds(currentCustomer.getUserId(), acct_num) != null) {

			List<Transaction> transactions = transactiondao.getTransactionsByIds(currentCustomer.getUserId(), acct_num);
				
			for(Transaction t: transactions) {
			
				System.out.println(t);
			
	
			} 
		
		} else {
			System.out.println("There are no transactions to show!");
		
		}
	}

	static void customerWithdrawal() {
		
		System.out.println("How much would you like to withdraw?");
		int amount = Integer.parseInt(sc.nextLine().trim());
		
		String acct_num;
	
		do {
		System.out.println("Which account would you like to withdraw from? (Enter your acct #)");
		acct_num = sc.nextLine().trim();
		
			if(accountdao.getAccountById(acct_num) == null) {
			
				System.out.println("Incorrect Account Number!");
			}
		}
		
		while(accountdao.getAccountById(acct_num) == null);
		
		Account temp = accountdao.getAccountById(acct_num);
		
		amount *= -1;
		
		temp.setBalance(amount + temp.getBalance());
		
		boolean updated = accountdao.updateAccount(temp);
		
		if(updated) {
			
			System.out.println("Withdraw successful!");
			System.out.println("Your new balance is: " + temp.getBalance());
		}
		
		transactiondao.addTransaction(new Transaction(0, "withdraw", amount, currentCustomer.getUserId(), acct_num));
		
	}
	
		
	static void customerDeposit() {
		
		System.out.println("How much would you like to deposit?");
		int amount = Integer.parseInt(sc.nextLine().trim());
		
		String acct_num;
	
		do {
		System.out.println("Which account would you like to deposit into? (Enter your acct #)");
		acct_num = sc.nextLine().trim();
		
			if(accountdao.getAccountById(acct_num) == null) {
			
				System.out.println("Incorrect Account Number!");
			}
		}
		
		while(accountdao.getAccountById(acct_num) == null);
		
		Account temp = accountdao.getAccountById(acct_num);
		
		temp.setBalance(amount + temp.getBalance());
		
		boolean updated = accountdao.updateAccount(temp);
		
		if(updated) {
			
			System.out.println("Deposit successful!");
			System.out.println("Your new balance is: " + temp.getBalance());
		}
		
		transactiondao.addTransaction(new Transaction(0, "deposit", amount, currentCustomer.getUserId(), acct_num));
		
	}
	
}
