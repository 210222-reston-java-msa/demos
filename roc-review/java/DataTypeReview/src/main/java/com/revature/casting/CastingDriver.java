package com.revature.casting;

import java.util.ArrayList;

public class CastingDriver {

	public static void main(String[] args) {
		
		/*Casting
		 * 
		 * Type casting is used to convert objects or variables of one type into another
		 * 
		 * Widening Casting (Implicit) -- Automatic Type Conversion
		 * Narrowing Casting (Explicit) -- Need Explicit Conversion with ()
		 */
		
		
		byte b = 40;// (source type)
		short s = b; // we are converting a byte INTO a short (This is our target type)
		int z = 2;
		long l = z;
		float f = l;
		double r = f;
		
		long someLong = (long) 100.9; // longs don't support decimals (only large integer values)
		System.out.println("The long is equal to: " + someLong);
		
		
		double dub = 900.9; // you use a double to handle deciamls (floating points)
		int myInt = (int) dub;
		
		int i = 100; // (int is our source and is SMALLER that double)
		double d = i; // so, our target has more space to take on this value
		
		// Casting is needed when we need to explicitly convert from a larger data type to a smaller one
		double x = 10.99;
		int y = (int) x; // this will "floor" an int
		
		System.out.println("The  value of the double casted to an int is " + y);
		
		System.out.println(s); // we will see 40 no problem
		
		System.out.println("***************************************");
		
		int num = 10; // Primitive data types and their ref variables are stored in the Stack
		
		// Wrapper Classes conver primitive data types into objects
		// Double, Short, Long...etc
		ArrayList<Integer> number = new ArrayList<Integer>();
		
		// We are creating an Integer OBJECT that has the value of our primitive data type
		// This process is referred to as Autoboxing
		// Autoboxing is the process of converting primitive datatypes into their corresponding objects
		Integer myObj = Integer.valueOf(num);
		
		System.out.println(num + " " + myObj);

		// Unboxing is the process of converting an object into its primitive datatype
		Integer myOtherObj = new Integer(10);
		System.out.println(myOtherObj);
		
		int myPrimitive =  myOtherObj.intValue(); // myOtherObj now points to a primitive datatype stored in the STACK.
		
		// To find the max value of an Integer
		System.out.println(Integer.MAX_VALUE);
	}

}
