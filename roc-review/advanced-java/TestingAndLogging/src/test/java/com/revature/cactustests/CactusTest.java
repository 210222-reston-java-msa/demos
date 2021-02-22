package com.revature.cactustests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.revature.models.Cactus;
import com.revature.models.Flower;

// This is the class where I will write my unit tests
public class CactusTest {
	
	// instantiate a cactus object to call tests on
	private static Cactus cactus;
	
	/*
	 * Unit Testing
	 * 
	 * Unit Testing means testing small units of your application.  Typically,
	 * these are methods.  We test our code to prove that the code works as expected.
	 * 
	 * You can also use unit testing to prove to the client or customer that your program indeed works
	 * and accounts for all types of errors.
	 */
	
	// Before annotation tells our application how to SET UP before running some tests
	@Before
	public void setUp() {
		// instantiated an array List with 0 flowers
		List<Flower> startingFlowers = new ArrayList<Flower>();
		
		// I add a flower to the list
		startingFlowers.add(Flower.Moonflower);
		
		// this is our guinea pig object that we can call tests on:
		// Notice that we pass the flowers list to it
		cactus = new Cactus(7, 2, "Green", startingFlowers);
	}
	

	// Here we will create a method to prove that our catcus's bloom method works
	@Test
	public void testBloom() {
		// first we create a cactus
		// when we instantiate the cactus, we give it some flower,
		// then we expect that testBloom would return the number of flowers
		assertEquals(1, cactus.bloom());
	}
	
	
	@Test
	public void testAdd() {
		// we are going to test that the add method returns the right amount
		assertEquals(10, Cactus.add(6, 4));
	}
	
	@Test
	public void testAdd2() {
		
		assertEquals(600, Cactus.add(200, 400));
		
	}
	
	/*
	 * TDD is when you write the tests BEFORE you write the code
	 */
	@Test
	public void testDivide() {
		
		assertEquals(5, cactus.divide(15, 3));
		
	}
	
	@Test
	public void doesCactusExist() {
		
		assertNotNull(cactus);
		
	}
	
	@Test
	public void growTest1() {
		
		Cactus c2 = new Cactus();
		
		c2.setHeight(3); // 3 + 0.02 = 3.02
		
		assertEquals(3.02, c2.grow(2.0), 0.001); // takes in 2 inches of rainfall
		
	}
	
	// What happens if I put a negative value into grow...
	// You must include the expected exception that will be thrown in the annotation itself.
	@Test(expected = IllegalArgumentException.class)
	public void negativeGrowthTest() {
		
		cactus.grow(-3.0);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
