package com.revature.tests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.revature.models.Employee;
import com.revature.repositories.EmployeeDAOImpl;
import com.revature.services.EmployeeService;

/*
 * ======== MOCKITO & JUNIT TESTING =========================
 * 
 * https://semaphoreci.com/community/tutorials/stubbing-and-mocking-with-mockito-2-and-junit
 * 
 */



public class ServiceTests {

	//class to be tested
	private EmployeeService  eserv;
	
	// dependencies that our service layer needs in order to make contact with the DB
	// later we will MOCK this and trick our application
	// into THINKING that it's connecting to a DB when its not.
	private EmployeeDAOImpl edaoImpl;
	

	@Before
	public void setup() {
		
		eserv = new EmployeeService();
		
		/*
		 * Here, we're creating a fake DB connection....
		 * and tricking our Service layer
		 */
		edaoImpl = mock(EmployeeDAOImpl.class);
		
		// here we set the EmployeeDAOImpl OF the Service 
		// to the one that we've mocked
		eserv.eDao = edaoImpl;
		
	}
	/*
	 * A happy path is a default scenario in which we
	 * allow it input and get expected output without exceptions
	 */
	
	@Test
	public void happyPathScenario() {
		
		Employee sampleEmployee = new Employee(1, "a", "b", "c", "d");
		
		// create a fake list representing the list of Employees pulled from the DB
		List<Employee> list = new ArrayList<Employee>();
		list.add(sampleEmployee);
		
		// We are essentially programming our "fake dao" to return this as its fake data
		when(edaoImpl.findAll()).thenReturn(list);
		
		String dummyUsername = sampleEmployee.getUsername();
		
		Employee employeeFoundByUsername = eserv.findByUsername(dummyUsername);
		
		// Here we're assuring that our Service Layer's findByUsername()
		// method will return the Employee obj that we supplied our fake persistence layer
		assertEquals(sampleEmployee, employeeFoundByUsername);
		
	}
	
	
	@Test
	public void employeeIsNotPresentInDB() {
	
		/*
		 * The findAll() method in our DAOimpl class will return a list
		 * (empty or populated) regardless -- so I need to "program" it to
		 * return the empty list in my test case.
		 */
		List<Employee> emptyList = new ArrayList<Employee>();
		
		when(edaoImpl.findAll()).thenReturn(emptyList);
		
		/*
		 * We are simply passing through data that we know doesn't exist 
		 * in our "list of employees"
		 */
		Employee employeeFoundByUsername = eserv.findByUsername("tester");
		
		
		assertEquals(null, employeeFoundByUsername);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
