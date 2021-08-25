package com.revature.models;

public class Course {
	
	private String name;
	
	public static final int costOfCourse = 600;

	public Course(String name) {
		super();
		this.setName(name);
	}

	// so that we can retrieve the name of the course when we want
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name.toUpperCase();
	}
	
	
	
}
