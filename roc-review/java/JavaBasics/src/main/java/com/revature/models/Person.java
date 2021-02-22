package com.revature.models;

/*
 * This is a class. A class is a blueprint, or a template, from which objects are created.
 * Objects can contain variables, methods, and nested classes.
 * 
 * If this particular class does not extend from another class, it extends the ROOT CLASS of Java
 * which is the Object class. 
 * 
 * Here, the Object class is implicitly extended.
 */
public class Person {
	
	// These are instance variables.
	private String firstName; // i"m using an access modifier to restrict access to this variable
	private String lastName;
	public int age;
	public double height_in;
	public double weight;
	
	// go to com.revature and see how we instantiate people
	
	// Our person object has no constructor...so that means we can create a person object simply 
	// by typing Person x = new Person();
	
	/*
	 * Constructor
	 * 
	 * A constructor is block of code that is called when an instance of an object is created. 
	 * It's LIKE a method,
	 * except that it doesn't have a return type.
	 */

	// This is a no-args constructor
	// this means that it doesn't take in any parameters to create an object.
	public Person() {
		
	}
//	
//	// If we want to create a Person object with INITIALIZED instance variables,
//	// then we create a parameterized constructor
//	
	public Person(String firstName, String lastName, int age, double height_in, double weight) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.height_in = height_in;
		this.weight = weight;
	}

	// Since Person extends from the Object class (which is the root of all classes in Java)...
	// Let's borrow some methods!
	
	// right click -> Generate toString() -> Finish
	@Override // Overriding is an example of Polymorphism in which a method can take on many forms
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", height_in=" + height_in
				+ ", weight=" + weight + "]";
	}
	
	/*
	 * static means that this method belongs to the class, NOT the individual object (specific instance)
	 * So, in order to call this method, I don't need to create a Person object.
	 */
	public static void eat(String food) {
		System.out.println("The person is eating a " + food);
	}
	
	
	public double calculateBMI(double height, double weight) {
		
		return (height/weight) * 703;
	}
	
	// To generate getters and setters
	// right click -> go Source -> generate Getters and setters
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	
	
}
