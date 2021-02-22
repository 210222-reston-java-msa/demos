package com.revature.casting;

public class ClassCastingDriver {

	public static void main(String[] args) {
		
		// Basic instantiation of a class looks like this:
		Parent parent = new Parent();
		parent.printSomething();
		
		
		Child child = new Child();
		child.printSomething();
		
		// Here we use the variable of type Parent to invoke a method that's only
		// available on the Child class.
		
		// We are DOWNCASTING...
		// because we're casting from a superclass to a subclass. 
		Parent aChildObject = new Child();
		aChildObject.printSomething();
		
		int x = 2;
		double newDub = Double.valueOf(x);
		
		
		// Here we are assigning a larger type to a smaller type 
		Child c = (Child) aChildObject;
		c.printSomething();
		
		System.out.println(c.toString());
		
		// Here we are also assigning a larger type to a smaller type
		double dub = 2.4;
		int num = (int) dub;
		
		// Object is the LARGEST type for all classes to be converted to
		String s = "hello";
		Object obj = s; // we can do this because Object is the superclass of String
		
		Object word = new String("hi");
		
		Object anotherWord = "goodbye";
		
		// if we want to convert a SUPERCLASS into a subclass, we must cast.
		String str2 = (String) new Object();
		
		System.out.println(obj);
		
		
		

	}

}
