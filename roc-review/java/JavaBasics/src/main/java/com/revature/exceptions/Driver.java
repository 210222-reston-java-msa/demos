package com.revature.exceptions;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Driver {
	
	/*
	 * Exceptions/Errors are issues that may occur in a Java program that keep our program from running smoothly.
	 * These are issues that can occur during runtime of the application.
	 * 
	 * Java categorizes these 2 "issues" into ERRORS and EXCEPTIONS:
	 * 
	 * -- Exceptions are "issues" that can be recovered from
	 * -- Errors are "issues" that cannot be recovered
	 * 
	 * Both Exceptions and Errors are represented in Java as Classes There is a THROWABLE class that is ontop of
	 * the exceptions hierarchy.
	 * 
	 * Throwable
	 * 
	 *	- Exception (checked)
	 *		- IOException (checked)
	 *			- FileNotFoundException (checked)
	 *
	 *		- RuntimeException (unchecked)
	 *			- ArtihmeticException (unchecked)
	 *			- NullPointerException (unchecked)
	 *
	 *	- Error (these are cuased by the environment where the app is running 
	 *			- StackOverflowError
	 *			- OutOfMemoryError
	 * 
	 */

	public static void main(String[] args) {
		int x = 10;
		int y = getIntEAFP();
		
		System.out.println(divideEAFP(x, y));
		
		System.out.println("I made it to this line!");

	}
	
	private static int divide(int x, int y) { 
		return x/y;
	}
	
	/*
	 * In programming there are two ways to prevent our application from crashing!
	 * 
	 * 1. Look Before You Leap (LBYL)
	 * 2. Easier to Ask for Forgiveness than Permission (EAFP)
	 */
	
	// LBYL
	private static int divideLBYL(int x, int y) { 
		if (y != 0) {
			return x/y;
		} else {
			return 0;
		}
		
	}
	
	// I want to safe-guard my code against crashing because I 
	// get an Arithmetic exception
	private static int divideEAFP(int x, int y) { 
		
		// SURROUND YOUR CODE IN A TRY/CATCH BLOCK
		try {
			return x/y;
		} catch (ArithmeticException e) {
			// the catch block determines how we want to recover
			System.out.println(e);
			return 0;
		}
			
	}
	
	// this is an unsafe method! Becasue a user could input a character and crash our program
	private static int getInt() {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Please enter an integer:");
		return scan.nextInt();
	}
	
	// Create a method called getIntEAFP() which returns 0 if a user puts in a value that is NOT an int
	private static int getIntEAFP() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter an integer:");
		
		try {
			return scan.nextInt();
		} catch (InputMismatchException e) {
			e.printStackTrace();
			return 0;
		} finally {
			// whatever is in a finally block will run...no matter what
			System.out.println("Finally has run!!!!");
		}
		
	}
	
}
