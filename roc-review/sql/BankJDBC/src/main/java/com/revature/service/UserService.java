package com.revature.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImpl;
import com.revature.models.Role;
import com.revature.models.User;

// The service layer is created to reinforce "business logic"
/*
 * All interaction with the DAO will be through our service layer.  
 * Business logic, is nothing more than code that is strictly in java, and doesn't
 * incorporate any SQL statements, or more complex logic that pertains to our database
 * 
 * Most other layers (like our ConnectionUtil, or our DAO interface and Implementation are
 * called "boilerplate code" -- The service layer is where the most creativity is involved.
 */

// Think of UserServic as BUILDING on the dao methods....
// If I wnat ot build on a dao method...I need a dao object...to first access the method

public class UserService {
	
	// calling the interface......// then the class that implements it to create a DAO object
	private static UserDao dao = new UserDaoImpl();
	
	
	// the starting place before you get creative, is to incorporate all CRUD methods.
	
	// If the add method is successful, it returns the number 1 
	public int add(User u) {
		
		return dao.insert(u);
	}
	
	public List<User> returnAllUsers() {
		
		return dao.findAll();
	}
	
	public User returnAUserById(int id) {
		return dao.findById(id);
	}
	
	public static void login(String username, String password) {
		
		// 1. Let's assign a User u equal to the User that the findByUSername method returns...
		User u = dao.findByUsername(username); // the dao's job is to query against the DB...We do the cheking (busines logic in
		// the service layer
		
		// 2. Check that the user that we return has the same password as the password passed into THIS method (login)
		if (password.equals(u.getPassword())) {
			
			System.out.println("you are logged in!");
			
		} else {
			
			System.out.println("Wrong password...");
		}
		
		
	}
	
	public static void register() {

		UserDao dao = new UserDaoImpl();// UserDaoImpl is a TYPE of UserDao

		// Calling methods from the service layer allows us more abstraction
		// we can incorporate extra code into our Service layer
		UserService us = new UserService();
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome to the galactic bank!\n Please enter your username");
		String inputUsername = scan.nextLine();
		System.out.println("Please enter your password:\n");
		String inputPassword = scan.nextLine();
		System.out.println("Please enter your first name:\n");
		String inputFirstName = scan.nextLine();
		System.out.println("Please enter your last name\n");
		String inputLastName = scan.nextLine();
		System.out.println("Please enter you email\n");
		String inputEmail = scan.nextLine();
		System.out.println("Are you a Admin or an Employee?\n Press [1] for Admin\n Press [2] for Employee:\n");

		int roleId;

		if (scan.nextInt() == 1) {
			roleId = 1;
		} else {
			roleId = 2;
		}

		Role r = new Role(roleId);
		if (r.getId() == 1) {
			r.setName("Admin");
		} else {
			r.setName("Employee");
		}

		User u = new User(inputUsername, inputPassword, inputFirstName, inputLastName, inputEmail, r);

		System.out.println(dao.insert(u));

		System.out.println(us.returnAllUsers());
	}
	
	
	

}
