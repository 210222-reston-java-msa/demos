package com.revature.exceptions;


// https://stackabuse.com/how-to-make-custom-exceptions-in-java/
public class NotAGradeException extends RuntimeException{

	public NotAGradeException(String message) {
		super(message);
	}

	
}
