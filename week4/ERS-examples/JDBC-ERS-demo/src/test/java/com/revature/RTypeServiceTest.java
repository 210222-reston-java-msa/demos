package com.revature;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.service.RTypeService;


public class RTypeServiceTest {
	
	private static Logger logger = LogManager.getLogger(RTypeServiceTest.class);
	RTypeService ts;
	
	@Before
	public void setUp() throws Exception{
		ts = new RTypeService();
	}
	
	@After
	public void tearDown() throws Exception{
		ts = null;
	}

	@Test
	public void test() {
		logger.debug(ts.getRTypeById(2));
		assertEquals("Travel", ts.getRTypeById(2));
	}

}
