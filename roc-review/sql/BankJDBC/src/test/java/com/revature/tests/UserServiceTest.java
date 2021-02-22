package com.revature.tests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.revature.dao.UserDaoImpl;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.service.UserService;

/*
 * Junit is for method testing (unit testing)
 * We use Mockito to MOCK our dao and test its functionalioty without actually
 * inserting objects into the database.
 */
public class UserServiceTest {
	
	// @Mock will create a mock implementation of the UserDaoImpl class
	@Mock 
	private UserDaoImpl daoMock;
	
	// @InjectMocks will give us the object (the UserDaoImpl) that the Service layer relies on.
	// @InjectMocks will inject the Mocks marked iwith @Mock to this instance when it is created.
	@InjectMocks
	private UserService serviceMock;
	
	@Before
	public void setUp() {
		/*
		 * Create an instance at the start of every test method
		 */
		MockitoAnnotations.initMocks(this);
	}
	
	// When we add a user into our database, what does the method return?
	// The method returns the number 1
	
	// This single unit test is just testing the add() method on my Service layer
	@Test
	public void testAddUser_return1() {
		
		// Step 1: create a dummy user object to insert into DB
		User user = Mockito.mock(User.class); // This creates a "fake object" that will NOT persist to our DB
		
		Role role = Mockito.mock(Role.class);
		role.setId(1);
		role.setName("BankAdmin");
		
		user.setRole(role); // Now my User's role is not null
		
		// Step 2: perform the insert method (from the DaoImpl layer)

		when(daoMock.insert(user)).thenReturn(1);
		// Step 3: perform the add method (from the Service layer)
		assertEquals(1, serviceMock.add(user));

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
