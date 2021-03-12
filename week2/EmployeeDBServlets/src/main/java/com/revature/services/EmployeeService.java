package com.revature.services;

import java.util.List;

import com.revature.models.Employee;
import com.revature.repositories.EmployeeDAO;
import com.revature.repositories.EmployeeDAOImpl;

public class EmployeeService {
	
	// EmployeeService class DEPENDS on the the EmployeeDAOImpl
	public static EmployeeDAO eDao = new EmployeeDAOImpl();
	
	
	public static boolean insert(Employee e) {
		
		return eDao.insert(e);
	}
	
	public static boolean update(Employee e) {
		
		return eDao.update(e);
	}
	
	public static List<Employee> findAll() {
		return eDao.findAll();
	}
	
	// find by username...
	public static Employee findByUsername(String username) {
		List<Employee> all = eDao.findAll();
		//List<Employee> all = findAll(); // another way to do it
		
		for (Employee e : all) { // filtering with an enhanced for-loop!
			if (e.getUsername().equals(username)) {
				return e; // we return the Employee object with a matching ID
			} else {
				continue;   // if username doesn't match the username prop of the Employee element
							// then continue coninue the loop to the next element.
			}
		}
		
		return null;
	}
	
	// confirm login method
	public static Employee confirmLogin(String username, String password) {
		
		// we use the above method
		Employee e = findByUsername(username);
		
		if (e == null) {
			return null;
		}
		
		if (e.getPassword().equals(password)) {
			return e;
		} else {
			return null;
		}
	}
	
	
	
	
	
	
	
	
	
	

}
