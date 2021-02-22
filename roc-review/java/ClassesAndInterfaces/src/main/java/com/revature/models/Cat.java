package com.revature.models;

// This is a concrete class
public class Cat extends Animal {

	private String breed; 
	private boolean hasFur;

	public Cat() {
		this.numberOfLives = 9;
	}
	
	public Cat(String breed, boolean hasFur) {
		this.breed = breed;
		this.hasFur = hasFur;
	}

	
	@Override // Overriding is the process of keeping the same method signature (name), and providing
	// a "custom" implementation for the class that inherits the method
	public void makeSound() {
		// TODO Auto-generated method stub (method without a body)
		System.out.println("Meow");
		
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public boolean isHasFur() {
		return hasFur;
	}

	public void setHasFur(boolean hasFur) {
		this.hasFur = hasFur;
	}
	
	// Polymorphism - Greek for many forms
	
	
	
}
