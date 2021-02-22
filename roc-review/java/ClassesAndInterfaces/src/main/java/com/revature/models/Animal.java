package com.revature.models;

/*
 * An abstract class is a class that CANNOT be instantiated.
 * Abstract classes often have abstract unimplemented methods that MUST be overidden by a concrete subclass.
 */
public abstract class Animal {
	
	public int legs;
	public String color;
	public int numberOfLives = 1;
	
	public Animal() {
		System.out.println("Animal constructor called!");
	}
	
	/*
	 * Abstract methods are methods that have no implementation (called a stub) with no body.
	 * Any concrete subclass that extends this abstract class MUST implement this method and override it.
	 */
	public abstract void makeSound();
	
	
	/*
	 * As was mentioned before, Abstract classes can have CONCRETE methods (it's a method with implementation)
	 * However, subclasses can still override these concrete methods with their own implementation if desired.
	 */
	public void exist() {
		System.out.println("The animal is existing");
	}
	
	

}
