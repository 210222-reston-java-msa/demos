package com.revature.exceptions;


// If we're createing a custom exception, we must extend Exception
public class InsufficientFundsException extends Exception{
	
	public InsufficientFundsException() {
		// WHEN the exception is thrown, a new InsufficientFundsException object is instantiated...
		// So, in the constructor, we tell it what to do when this happens
		System.out.println("Insufficient Funds!");
	}

}
