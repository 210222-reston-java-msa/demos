package com.revature;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.service.UserService;

public class UserServiceTest {
	
	private static Logger logger = LogManager.getLogger(UserServiceTest.class);
	
	UserService us;
	
	@Before
	public void setUp() throws Exception{
		us = new UserService();
	}
	
	@After
	public void tearDown() throws Exception{
		us = null;
	}

//	@Test
//	public void test() {
//		List<String> emails = us.getAllEmails();
//		for (String e : emails) {
//			logger.debug(e);
//		}
//		assertNotNull(emails);
//		assertFalse(emails.size() == 0);
//	}
	
}
