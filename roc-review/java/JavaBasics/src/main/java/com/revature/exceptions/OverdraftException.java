package com.revature.exceptions;

public class OverdraftException extends RuntimeException{
	
	public OverdraftException() {
		super();
		System.out.println("You don't have enough to withdraw that amount");
	}

}
