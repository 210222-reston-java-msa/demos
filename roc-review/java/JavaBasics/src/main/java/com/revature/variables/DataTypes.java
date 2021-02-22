package com.revature.variables;

public class DataTypes {

	// The main method is the entry point of our application
	public static void main(String[] args) {

		/*
		 * Java has 8 primitive datatypes
		 * -- these are NOT objects
		 * -- But every other value within Java is an object
		 * 
		 * There are also non-primitive datatypes (Strings, Arrays, Objects, etc.)
		 */
		
		// a variable is where you store data
		int x = 10;
		String name = "Larry";
		
		System.out.println(name);
		System.out.println(x);
		
		// this is a reference variable 
		byte reallySmallNumber = 127; // 8 bits of reserved memory The max value for a byte  is 127 and minimum -127 
		short shortNumber = 32767; // 16 bits of reserved memory 
		int var = 100000000; // 32 bits of reservered memory
		long bigNumber = 123456789L; // 64 bits of memory
		
		float anotherNum = 93.2f; // 32 bits of memory
		double balance = 10000000.87; // 64 bits of memory 
		//int bal = 10.23; ints can never be doubles or decimal numbers
		
		boolean isOpen = false; // a boolean represents true or false values (1 bit of memory) 
		char letter = 'm'; // 16 bits of memory that represent an ASCII character's numeric value 
		
		// Wrapper class is the class name of the data type.  Essentially it is the name of the 
		// object
		byte min = Byte.MIN_VALUE;
		byte max = Byte.MAX_VALUE;
		System.out.println("The minimum value of a byte is " + min + " and the max value is " + max);
		
		int biggestInt = Integer.MAX_VALUE;
		System.out.println(biggestInt);
		
		int y = 100;
		int z = 4000;
		System.out.println(y + z);
		
		String one = "1";
		
		// Challenge: convert our String called one into and integer
		// then print the value of it plus 2.
		
		// Answer:
		int n = Integer.parseInt(one); // Integer.parseInt returns primitive value
		// Integer.valueOf returns the actual object.
		System.out.println(n + 2);
		
		int myNum;
		myNum = 8000;
		
		myNum = myNum + 1;// 8001
		myNum++; //^ this is the same as above; // 8002
		myNum--; // this subtracts 1
		System.out.println(myNum);
		
		
		
	}

}
