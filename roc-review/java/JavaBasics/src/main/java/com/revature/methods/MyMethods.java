package com.revature.methods;

// a class is a blueprint for an object
public class MyMethods {
	
	// the method signature tells us how to access it, and it tells us its return type
	// this is the signature of the method
	// void means that this method does not return anything of a particular datatype
	// rather void means that it does something
	static void myPrintMethod(String word) {
		
		// in this method, we'll take in some data, and print it to the console.
		
		String newString = word + "extralettersmuahahahaa";
		
		System.out.println(newString);
	}
	
	static void addTwo(int x) { 
		// this method takes in an integer
		// - it adds 2 to the int
		// then it System.out.println
		
		//alt + shift + x..x.wait a second, then press j (keyboard shortcut to run a java program)
		
//		int newNumber = x + 2;  This works.... but the below method is much faster
//		
//		System.out.println(newNumber);
		
		System.out.println(x + 2);
	}
	
	static int multipliedByFour(int x) {
		
		return x * 4;
		
		// this method will take in an int
		// it will multiply it by 4
		// then it will return the value
	}
	
	
}
