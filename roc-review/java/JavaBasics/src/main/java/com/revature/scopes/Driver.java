package com.revature.scopes;

public class Driver {
	
	/*
	 * There are 4 scopes in Java
	 * 
	 * Each scope is defined with curly brackets { }
	 * 
	 * Instance Scope: Variables/Methods that belong to an object itself (the instance of a class)
	 * Static Scope: Variables/Methods that belong to the class itself (the blueprint)
	 * 
	 * The following two methods only apply to VARIABLES:
	 
	 * Method Scope: The outermost scope of a method body
	 * Block Scope: Further nested scopes inside of a method (think of a for-loop)
	 */

	public int x; // Instance scope
	public static int y; // Static scope -- this is shared by all objects that are an instance of the Driver class
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static void printX(Driver d) {
		// This method will set the x variable of a Driver object to 25.
		d.x = 25;
		
	}

}
