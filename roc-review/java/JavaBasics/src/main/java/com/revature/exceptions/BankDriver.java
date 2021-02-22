package com.revature.exceptions;

public class BankDriver {

	public static void main(String[] args) {
		
		try {
			System.out.println("The amount left after withdrawing is: " + withdraw(1000, -3000));
		} catch (OverdraftException e) {
			System.out.println("Caught the exception!");
		} finally {
			System.out.println("finally runs!");
		}
 	

	}
	
	public static double withdraw(double balance, double amountToWithdraw) throws OverdraftException{
		
		if (amountToWithdraw < 0) {
			System.out.println("Please try again");
			return balance;
		}
		
		if (amountToWithdraw > balance) {
			throw new OverdraftException();
		}
		
		System.out.println("We withdrew succesfully!");

		double remainingBalance = balance - amountToWithdraw;		
		return remainingBalance;
		
	}

}
