package com.revature.wrapperclasses;

public class Driver {

	public static void main(String[] args) {
		
		
		/*
		 * Wrapper Classes
		 * 
		 * For every primtive type in Java, there is a built-in object type called a wrapper class
		 * 
		 * For example the built-in Wrapper Class for int is called Integer
		 */
		System.out.println("The min value of an int is " + Integer.MIN_VALUE);
		
		/*
		 * Autoboxing is the automatic conversion that the Java compiler makes between primitive types and their
		 * corresponding object wrapper class.
		 * 
		 */
		
		char d = 'd'; //primitive datatype
		System.out.println(d);
		
		Character autoboxedChar = 'd'; 
		System.out.println(autoboxedChar);
		
		// conversion the other way is called unboxing.
		
	}

}
