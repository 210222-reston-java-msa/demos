package com.revature;

import com.revature.models.Person;

public class World {

	public static void main(String[] args) {
	
		// to instantiate an object of a CLASS type, you do the following
		Person bob = new Person();
		Person sally = new Person();
		Person frankenstein = new Person();
		
		// an object is eligible for garbage collection when a reference variable no longer points to it
		// in Java memory management is automated by a process called garbage collection.
		
		// garbage collection is non-deterministic (we can't control)
		// but we can suggest that garbage collection remove an object with System.gc()
		
		// System.gc();
		
//		bob.lastName = "Smith";
		
		// instead of hard coding in a last name, we can create something called a constructor
		
		// here we are printing out the instance variable of bob's last name
//		System.out.println(bob.lastName);
		
		System.out.println(bob);
		System.out.println(sally);
		
		Person googleCEO = new Person("Larry", "Page", 60, 68, 180);
//		
//		System.out.println("Google CEO name is " + googleCEO.firstName); // I have restricted access because i changed
		// the access modifier.
		System.out.println(googleCEO);
		
		// googleCEO.eat("apple");
		
		Person.eat("apple"); // since eat() is static, I don't need to create an object to call it
		
		double BMI = googleCEO.calculateBMI(googleCEO.height_in, googleCEO.weight);
		System.out.println("The google CEO's BMI is: " + BMI);
		
		System.out.println(googleCEO.getFirstName());
	}

}
