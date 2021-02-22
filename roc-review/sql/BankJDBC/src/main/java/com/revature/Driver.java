package com.revature;

import java.util.Scanner;

import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImpl;
import com.revature.service.UserService;

public class Driver {

	public static void main(String[] args) {
		
		run();
		
		UserDao dao = new UserDaoImpl();
		
		// this method I will need to work on
		// findByID DOES work
		System.out.println(dao.findByUsername("skywalker99"));
		
//		Account lukesAccount = new Account(1, 2, 1, 100, 2);
//		
//		try {
//			lukesAccount.withdraw(2000); // the throws keyword forces us to handle this wherever it's called
//		} catch (InsufficientFundsException e) {
//			e.printStackTrace();
//		} 
		
	}

	public static void run() {
		Scanner scan = new Scanner(System.in);

		System.out.println("Welcome to the Bank!\n [1] To register\n [2] To login\n");
		
		int choice = scan.nextInt();
		
		UserService us = new UserService();
		 
		if(choice == 1) {
			UserService.register();
		} else if (choice == 2){
			scan.nextLine();
			System.out.println("Enter your username:\n");
			
			String username = scan.nextLine();
		
			
			System.out.println("Enter your password:\n");
			
			String password = scan.nextLine();
			
			UserService.login(username, password);
			// In a sign in method, I would have to use a method from my UserService or my
			// DaoImpl class.....
			
			// I would have to query both the username AND the password, and make sure they match.
		}

	}

	

}
