package com.revature.methods;

public class Driver {

	public static void main(String[] args) {
		// In our main method we will run the methods that we import from MyMethods
		
		MyMethods.myPrintMethod("Hello there");
		
		String dog = "Greyhound";
		MyMethods.myPrintMethod(dog);
		
		MyMethods.addTwo(100);
		
		int n = 9000;
		MyMethods.addTwo(n);
		
		int j = MyMethods.multipliedByFour(10);
		System.out.println("The multipled number is " + j);
		
	}
	
	

	/*
	 * Methods:
	 * A method is nothing more than a block of code that runs when called.
	 * You can pass data in between parentheses ()..the data passed through is called
	 * PARAMETERS.
	 * 
	 * Methods are used to perform a certain action.
	 */
}
