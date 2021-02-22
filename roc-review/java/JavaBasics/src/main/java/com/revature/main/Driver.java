package com.revature.main;

public class Driver {

	public static void main(String[] arg) {
		
		// The JVM will search for the main method with this exact method signature
		// This is the entry point of our application
		
		/*
		 * public - access-modifier: This means that we can access this method ANYWHERE within our application
		 * 
		 * static - non-access modifier: This means that we can invoke (call on) this method WITHOUT
		 * 			creating an instance of the Driver class.
		 * 
		 * void - this is a keyword that specified the return type.  Main() does not return a value
		 * 
		 * main is the name of the method that the JVM will look for
		 * 
		 * the String[] parameter which is referred to as args refers to the the collection of
		 * commands that I write within the main method.
		 */
		
		Driver d = new Driver(); // here I am creating an instance of the class (but there's no need to do this
		//Driver.main(args); // but since the main() method is static, I don't need to create an object to invoke it
		//d.main(args); 
		
		System.out.println("Hello world!");
	}

}
