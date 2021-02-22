package com.revature;

public class Associate {

	private int id;
	private String firstName;
	private String lastName;
	
	public Associate() {}

	public Associate(int id, String firstname, String lastName) {
		super();
		this.id = id;
		this.firstName = firstname;
		this.lastName = lastName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstName;
	}

	public void setFirstname(String firstname) {
		this.firstName = firstname;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "Associate [id=" + id + ", firstname=" + firstName + ", lastName=" + lastName + "]";
	};
	
	
	
}
