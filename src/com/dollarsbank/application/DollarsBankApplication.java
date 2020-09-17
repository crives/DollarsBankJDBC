package com.dollarsbank.application;
import java.util.Scanner;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;

import com.dollarsbank.model.Account;
import com.dollarsbank.dao.AccountDAO;
import com.dollarsbank.dao.AccountDAOImp;
import com.dollarsbank.model.Customer;

public class DollarsBankApplication {

	public static void main(String args[]) {
		
	Account acct = new Account();
	
	int choice = 0;
	boolean invalidInput;
	Scanner sc = new Scanner(System.in);
	Customer customer = null;
	Account account = null;
	
//	do {
		do {
//	while(true) {
		System.out.println("---- Welcome to Dollars Bank! ----");
		System.out.println("\n1. Create New Account");
		System.out.println("\n2. Login");
		System.out.println("\n3. Exit.");
		System.out.println("");
		System.out.println("\nEnter Choice (1, 2, or 3) :");
		
		choice = Integer.parseInt(sc.nextLine().trim());
		
		} while(choice > 3 || choice < 0 && choice != 3);
		
		switch(choice) {
		case 1:
			System.out.println("Create a New Account");
			System.out.println("\nEnter your name.");
			String name = sc.nextLine().trim();
			account.setName(name);
			
			System.out.println("\nCreate a UserId: ");
			String id = sc.nextLine().trim();
			account.setUserId(id);
			
			System.out.println("\nCreate a password (8 characters with lower, upper, and special): ");
			String pass = sc.nextLine().trim();
			account.setPassword(pass);
			
			System.out.println("\nEnter an initial deposit amount: ");
			Long deposit = sc.nextLong();
			account.setInitialDeposit(deposit);
		
//			try {
//				a.set
//			}
			break;
		case 2: 
			System.out.println("Login");
			System.out.println("\nEnter UserId: ");
			String userId = sc.nextLine().trim();
//			if .getAccountByUserId(userId);
			
			System.out.println("\nEnter Password: ");
			String password = sc.nextLine().trim();
			
//			if( ) {
				
				
//			}

//			
//			if()
//			b = account.getUserId();
			
			
			break;
		case 3: 
			System.out.println("Exit");
			break;
	}
	
		}
//	}
	
}