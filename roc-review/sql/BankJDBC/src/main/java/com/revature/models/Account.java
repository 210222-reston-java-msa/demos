package com.revature.models;

import com.revature.exceptions.InsufficientFundsException;

public class Account {
	
	private int accId; // Primary key
	private int usrId; // Foreign key that reference the id column (primary key) of the user table
	private int accType; // Foreign key that references the ACC_Types table
	private double balance;
	private int active; // 1 could be pending, 2, active, 3 closed -- could also be a boolean
	
	public Account() {
		
	}
	
	public Account(int accId, int usrId, int accType, double balance, int active) {
		super();
		this.accId = accId;
		this.usrId = usrId; // this points to the User who owns this account
		this.accType = accType;
		this.balance = balance;
		this.active = active;
	}

	
	public double withdraw(double amount) throws InsufficientFundsException{
		if (amount > balance) {
			throw new InsufficientFundsException(); // I could create my own exceptions
		} 
		
		if (balance - amount < 10) {
			System.out.println("Warning! You have less than $10 in your account.");
		}
		
		balance -= amount; // This is equal to balance = balance - amountToWithdraw;
		return balance;
	}
	
	public double deposit(double amount) {
		// some other method is running your menu options
		
		if (amount < 0) {
			System.out.println("Please enter a positive amount\n");	
		}
		
		balance += amount;
		
		return balance; //You could return balance, then use a System.out.println(); statment to print the new amount. 
		
	}
	
	
	
	
}
