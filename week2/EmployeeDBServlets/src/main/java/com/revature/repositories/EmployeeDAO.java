package com.revature.repositories;

import java.util.List;

import com.revature.models.Employee;

/*
 * DAO stands for dsata access object -- the object (in impl form) that we use to access our DB
 * We use the DAO design pattern to separate business logic (java) from our persistence layer
 */
public interface EmployeeDAO {
	// DAO is for CRUD methods 
	public boolean insert(Employee e); // returns true if successfully inserted	
	public boolean update(Employee e);
	
	public List<Employee> findAll(); // this will return ALL employee objects from the DB
	// we will use this in our service layer and filter it to return JUST one employee that matches
	// username + password
	
	

}
