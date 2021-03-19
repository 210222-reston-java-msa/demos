package com.revature.models;

public class User {
	
	private int userId;
	private String email;
	private String pwd;
	private String firstName;
	private String lastName;
	private int roll;
	private int approved;
	
	public User() {}

	public User(String email, String pwd, String firstName, String lastName, int roll) {
		super();
		this.email = email;
		this.pwd = pwd;
		this.firstName = firstName;
		this.lastName = lastName;
		this.roll = roll;
		this.approved = 0;		// Need manager approval
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

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

	public int getRoll() {
		return roll;
	}

	public void setRoll(int roll) {
		this.roll = roll;
	}

	public int getApproved() {
		return approved;
	}

	public void setApproved(int approved) {
		this.approved = approved;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", email=" + email + ", pwd=" + pwd + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", roll=" + roll + ", approved=" + approved + "]";
	}
	

	

}
