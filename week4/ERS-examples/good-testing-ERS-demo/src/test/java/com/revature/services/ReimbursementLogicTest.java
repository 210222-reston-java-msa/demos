package com.revature.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.TreeMap;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.revature.models.ReimStatus;
import com.revature.models.ReimTypes;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementInput;
import com.revature.repositories.ReimbursementsDAOImpl;

public class ReimbursementLogicTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	
	
//	@RunWith(MockitoJUnitRunner.class)
//	public class MockedTest {
//		@InjectMocks
//		ReimbursementLogic rLogic;
//
//		@Mock
//		ReimbursementsDAOImpl rDAOMock;

		ReimbursementsDAOImpl rDAOMock = mock(ReimbursementsDAOImpl.class);
		ReimbursementLogic rLogic = new ReimbursementLogic(rDAOMock);
		/*
		@Test
		public void CreattttttttttttttttttteNewReimbursementTest() {
			ReimbursementInput rInput = new ReimbursementInput(25.00, "Test", 1);
			int author = 3;
			byte[] file = {1,2,3,4,5};
			Reimbursement reim = new Reimbursement(rInput);
			reim.setAuthor(author);
			reim.setReceipt(file);
			when(rDAOMock.createReimbursement(reim)).thenReturn(true);
			
			assertTrue(rLogic.createNewReimbursement(rInput, author, file));
		}
		*/
	
		/**/
		@Test
		public void testCreateNewReimbursementGood() {
			
			ReimbursementsDAOImpl rDAOMock = mock(ReimbursementsDAOImpl.class);
			ReimbursementLogic rLogic = new ReimbursementLogic(rDAOMock);
			
			ReimbursementInput rInput = new ReimbursementInput(25, "Test", 1);
			int author = 3;
			byte[] file = {1,2,3,4,5};
			Reimbursement reimbursement = new Reimbursement(rInput);
			
//			when(rLogic = new ReimbursementLogic(rDAOMock)).thenReturn(true);
			when(rDAOMock.createReimbursement(reimbursement)).thenReturn(true);
			//boolean created = rLogic.createNewReimbursement(rInput, author, file);
			assertNotNull(rLogic.createNewReimbursement(rInput, author, file));

		}
		
		@Test
		public void testCreateNewReimbursementBadNotValid() {
			
			ReimbursementsDAOImpl rDAOMock = mock(ReimbursementsDAOImpl.class);
			ReimbursementLogic rLogic = new ReimbursementLogic(rDAOMock);
			
			ReimbursementInput rInput = new ReimbursementInput(-25, "Test", 1);
			int author = 3;
			byte[] file = {1,2,3,4,5};
			Reimbursement reimbursement = new Reimbursement(rInput);
			
//			when(rLogic = new ReimbursementLogic(rDAOMock)).thenReturn(true);
			when(rDAOMock.createReimbursement(reimbursement)).thenReturn(true);
			//boolean created = rLogic.createNewReimbursement(rInput, author, file);
			assertFalse(rLogic.createNewReimbursement(rInput, author, file));

		}
//	}


	/*
	 * **********************Status to Enum******************
	 */
	@Test
	public void testStatus0ToEnumPendingGood() {
		ReimbursementLogic rLogic = new ReimbursementLogic();
		assertEquals(ReimStatus.Pending, rLogic.statusToEnum(0));
	}

	@Test
	public void testStatus0ToEnumPendingBad() {
		ReimbursementLogic rLogic = new ReimbursementLogic();
		assertNotEquals(ReimStatus.Approved, rLogic.statusToEnum(0));
	}
	
	@Test
	public void testStatus0ToEnumPendingBad2() {
		ReimbursementLogic rLogic = new ReimbursementLogic();
		assertNotEquals(ReimStatus.Denied, rLogic.statusToEnum(0));
	}
	
	@Test
	public void testStatus1ToEnumApprovedGood() {
		ReimbursementLogic rLogic = new ReimbursementLogic();
		assertEquals(ReimStatus.Approved, rLogic.statusToEnum(1));
	}

	@Test
	public void testStatus1ToEnumApprovedBad() {
		ReimbursementLogic rLogic = new ReimbursementLogic();
		assertNotEquals(ReimStatus.Pending, rLogic.statusToEnum(1));
	}
	
	@Test
	public void testStatus1ToEnumApprovedBad2() {
		ReimbursementLogic rLogic = new ReimbursementLogic();
		assertNotEquals(ReimStatus.Denied, rLogic.statusToEnum(1));
	}
	
	@Test
	public void testStatusMinus1ToEnumDeniedGood() {
		ReimbursementLogic rLogic = new ReimbursementLogic();
		assertEquals(ReimStatus.Denied, rLogic.statusToEnum(-1));
	}
	
	@Test
	public void testStatusMinus1ToEnumDeniedBad() {
		ReimbursementLogic rLogic = new ReimbursementLogic();
		assertNotEquals(ReimStatus.Pending, rLogic.statusToEnum(-1));
	}
	
	@Test
	public void testStatusMinus1ToEnumDeniedBad2() {
		ReimbursementLogic rLogic = new ReimbursementLogic();
		assertNotEquals(ReimStatus.Approved, rLogic.statusToEnum(-1));
	}

	/*
	 * *********************Enum to Status***********************
	 */
	@Test
	public void testPendingEnumToStatus0Good() {
		ReimbursementLogic rLogic = new ReimbursementLogic();
		assertEquals(0, rLogic.enumToStatus(ReimStatus.Pending));
	}
	
	@Test
	public void testPendingEnumToStatus0Bad() {
		ReimbursementLogic rLogic = new ReimbursementLogic();
		assertNotEquals(1, rLogic.enumToStatus(ReimStatus.Pending));
	}
	
	@Test
	public void testPendingEnumToStatus0Bad2() {
		ReimbursementLogic rLogic = new ReimbursementLogic();
		assertNotEquals(-1, rLogic.enumToStatus(ReimStatus.Pending));
	}

	@Test
	public void testApprovedEnumToStatus1Good() {
		ReimbursementLogic rLogic = new ReimbursementLogic();
		assertEquals(1, rLogic.enumToStatus(ReimStatus.Approved));
	}
	
	@Test
	public void testApprovedEnumToStatus1Bad() {
		ReimbursementLogic rLogic = new ReimbursementLogic();
		assertNotEquals(0, rLogic.enumToStatus(ReimStatus.Approved));
	}
	
	@Test
	public void testApprovedEnumToStatus1Bad2() {
		ReimbursementLogic rLogic = new ReimbursementLogic();
		assertNotEquals(-1, rLogic.enumToStatus(ReimStatus.Approved));
	}
	
	@Test
	public void testDeniedEnumToStatusMinus1Good() {
		ReimbursementLogic rLogic = new ReimbursementLogic();
		assertEquals(-1, rLogic.enumToStatus(ReimStatus.Denied));
	}
	
	@Test
	public void testDeniedEnumToStatusMinus1Bad() {
		ReimbursementLogic rLogic = new ReimbursementLogic();
		assertNotEquals(1, rLogic.enumToStatus(ReimStatus.Denied));
	}
	
	@Test
	public void testDeniedEnumToStatusMinus1Bad2() {
		ReimbursementLogic rLogic = new ReimbursementLogic();
		assertNotEquals(0, rLogic.enumToStatus(ReimStatus.Denied));
	}
	/*
	 * *********************Type to Enum**********************
	 */
	
	@Test
	public void testType1ToEnumLodgingGood() {
		ReimbursementLogic rLogic = new ReimbursementLogic();
		assertEquals(ReimTypes.Lodging, rLogic.typeToEnum(1));
	}
	
	@Test
	public void testType1ToEnumLodgingBad() {
		ReimbursementLogic rLogic = new ReimbursementLogic();
		assertNotEquals(ReimTypes.Food, rLogic.typeToEnum(1));
	}
	
	@Test
	public void testType1ToEnumLodgingBad2() {
		ReimbursementLogic rLogic = new ReimbursementLogic();
		assertNotEquals(ReimTypes.Travel, rLogic.typeToEnum(1));
	}
	
	@Test
	public void testType1ToEnumLodgingBad3() {
		ReimbursementLogic rLogic = new ReimbursementLogic();
		assertNotEquals(ReimTypes.Other, rLogic.typeToEnum(1));
	}
	
	@Test
	public void testType2ToEnumTravelGood() {
		ReimbursementLogic rLogic = new ReimbursementLogic();
		assertEquals(ReimTypes.Travel, rLogic.typeToEnum(2));
	}
	
	@Test
	public void testType2ToEnumTravelBad() {
		ReimbursementLogic rLogic = new ReimbursementLogic();
		assertNotEquals(ReimTypes.Food, rLogic.typeToEnum(2));
	}
	
	@Test
	public void testType2ToEnumTravelBad2() {
		ReimbursementLogic rLogic = new ReimbursementLogic();
		assertNotEquals(ReimTypes.Lodging, rLogic.typeToEnum(2));
	}
	
	@Test
	public void testType2ToEnumTravelBad3() {
		ReimbursementLogic rLogic = new ReimbursementLogic();
		assertNotEquals(ReimTypes.Other, rLogic.typeToEnum(2));
	}
	
	@Test
	public void testType3ToEnumFoodGood() {
		ReimbursementLogic rLogic = new ReimbursementLogic();
		assertEquals(ReimTypes.Food, rLogic.typeToEnum(3));
	}
	
	@Test
	public void testType3ToEnumFoodBad() {
		ReimbursementLogic rLogic = new ReimbursementLogic();
		assertNotEquals(ReimTypes.Travel, rLogic.typeToEnum(3));
	}
	
	@Test
	public void testType3ToEnumFoodBad2() {
		ReimbursementLogic rLogic = new ReimbursementLogic();
		assertNotEquals(ReimTypes.Lodging, rLogic.typeToEnum(3));
	}
	
	@Test
	public void testType3ToEnumFoodBad3() {
		ReimbursementLogic rLogic = new ReimbursementLogic();
		assertNotEquals(ReimTypes.Other, rLogic.typeToEnum(3));
	}
	
	
	@Test
	public void testType4ToEnumOtherGood() {
		ReimbursementLogic rLogic = new ReimbursementLogic();
		assertEquals(ReimTypes.Other, rLogic.typeToEnum(4));
	}
	
	@Test
	public void testType4ToEnumOtherBad() {
		ReimbursementLogic rLogic = new ReimbursementLogic();
		assertNotEquals(ReimTypes.Travel, rLogic.typeToEnum(4));
	}
	
	@Test
	public void testType4ToEnumOtherBad2() {
		ReimbursementLogic rLogic = new ReimbursementLogic();
		assertNotEquals(ReimTypes.Lodging, rLogic.typeToEnum(4));
	}
	
	@Test
	public void testType4ToEnumOtherBad3() {
		ReimbursementLogic rLogic = new ReimbursementLogic();
		assertNotEquals(ReimTypes.Food, rLogic.typeToEnum(4));
	}

	/*
	 * *********************Enum to Type**********************
	 */
	@Test
	public void testLodgingEnumToTypeGood() {
		ReimbursementLogic rLogic = new ReimbursementLogic();
		assertEquals(1, rLogic.enumToType(ReimTypes.Lodging));
	}
	@Test
	public void testLodgingEnumToTypeBad() {
		ReimbursementLogic rLogic = new ReimbursementLogic();
		assertNotEquals(2, rLogic.enumToType(ReimTypes.Lodging));
	}
	@Test
	public void testLodgingEnumToTypeBad2() {
		ReimbursementLogic rLogic = new ReimbursementLogic();
		assertNotEquals(3, rLogic.enumToType(ReimTypes.Lodging));
	}
	@Test
	public void testLodgingEnumToTypeBad3() {
		ReimbursementLogic rLogic = new ReimbursementLogic();
		assertNotEquals(4, rLogic.enumToType(ReimTypes.Lodging));
	}
	
	@Test
	public void testTravelEnumToTypeGood() {
		ReimbursementLogic rLogic = new ReimbursementLogic();
		assertEquals(2, rLogic.enumToType(ReimTypes.Travel));
	}
	@Test
	public void testTravelEnumToTypeBad() {
		ReimbursementLogic rLogic = new ReimbursementLogic();
		assertNotEquals(1, rLogic.enumToType(ReimTypes.Travel));
	}
	@Test
	public void testTravelEnumToTypeBad2() {
		ReimbursementLogic rLogic = new ReimbursementLogic();
		assertNotEquals(3, rLogic.enumToType(ReimTypes.Travel));
	}
	@Test
	public void testTravelEnumToTypeBad3() {
		ReimbursementLogic rLogic = new ReimbursementLogic();
		assertNotEquals(4, rLogic.enumToType(ReimTypes.Travel));
	}

	@Test
	public void testFoodEnumToTypeGood() {
		ReimbursementLogic rLogic = new ReimbursementLogic();
		assertEquals(3, rLogic.enumToType(ReimTypes.Food));
	}
	@Test
	public void testFoodEnumToTypeBad() {
		ReimbursementLogic rLogic = new ReimbursementLogic();
		assertNotEquals(1, rLogic.enumToType(ReimTypes.Food));
	}
	@Test
	public void testFoodEnumToTypeBad2() {
		ReimbursementLogic rLogic = new ReimbursementLogic();
		assertNotEquals(2, rLogic.enumToType(ReimTypes.Food));
	}
	@Test
	public void testFoodEnumToTypeBad3() {
		ReimbursementLogic rLogic = new ReimbursementLogic();
		assertNotEquals(4, rLogic.enumToType(ReimTypes.Food));
	}

	@Test
	public void testOtherEnumToTypeGood() {
		ReimbursementLogic rLogic = new ReimbursementLogic();
		assertEquals(4, rLogic.enumToType(ReimTypes.Other));
	}
	@Test
	public void testOtherEnumToTypeBad() {
		ReimbursementLogic rLogic = new ReimbursementLogic();
		assertNotEquals(1, rLogic.enumToType(ReimTypes.Other));
	}
	@Test
	public void testOtherEnumToTypeBad2() {
		ReimbursementLogic rLogic = new ReimbursementLogic();
		assertNotEquals(2, rLogic.enumToType(ReimTypes.Other));
	}
	@Test
	public void testOtherEnumToTypeBad3() {
		ReimbursementLogic rLogic = new ReimbursementLogic();
		assertNotEquals(3, rLogic.enumToType(ReimTypes.Other));
	}

	/*
	 * ************************GrabCompleteReimbursements******************************
	 */
	@Test
	public void testGrabCompleteReimbursementsGood() {
		byte[] file = {1,2,3,4};
		Reimbursement reim = new Reimbursement(1, 25.00, "8-25-19", "8-26-19", "Test", file, 1, 1, 1, 1);
		reim.setStatus(rLogic.statusToEnum(reim.getStatusNum()));
		reim.setType(rLogic.typeToEnum(reim.getTypeNum()));
		TreeMap<Integer, Reimbursement> reims = new TreeMap<>();
		reims.put(1, reim);
		when(rDAOMock.getReimbursementsFromUserId(1)).thenReturn(reims);
		assertEquals(reims, rLogic.grabCompleteReimbursements(1));
	}
	
	
	@Test
	public void testGrabCompleteReimbursementsEmptyGood() {
		TreeMap<Integer, Reimbursement> reims = new TreeMap<>();
		when(rDAOMock.getReimbursementsFromUserId(1)).thenReturn(reims);
		assertEquals(reims, rLogic.grabCompleteReimbursements(1));
	}
	/*
	 * ******************Create Submission Time***********************
	 */
	@Test
	public void testCreateSubmissionTime() {
		ReimbursementLogic rLogic = new ReimbursementLogic();
		assertNotNull(rLogic.createSubmissionTime());
	}

	/*
	 * ********************Valid Reimbursement**********************
	 */
	@Test
	public void testIsValidReimbursementPositiveTwoDecimals() {
		ReimbursementLogic rLogic = new ReimbursementLogic();
		Reimbursement reim = new Reimbursement();
		reim.setAmount(100.00);
		assertTrue(rLogic.isValidReimbursement(reim));
	}
	@Test
	public void testIsValidReimbursementPositiveSixDecimals() {
		ReimbursementLogic rLogic = new ReimbursementLogic();
		Reimbursement reim = new Reimbursement();
		reim.setAmount(100.123456);
		assertTrue(rLogic.isValidReimbursement(reim));
	}
	@Test
	public void testIsValidReimbursementNegative() {
		ReimbursementLogic rLogic = new ReimbursementLogic();
		Reimbursement reim = new Reimbursement();
		reim.setAmount(-45);
		assertFalse(rLogic.isValidReimbursement(reim));
	}

	/*
	 * ***********************GrabAllCompleteReimbursements***************************************
	 */
	@Test
	public void testGrabAllCompleteReimbursementsGood() {
		byte[] file = {1,2,3,4};
		Reimbursement reim = new Reimbursement(1, 25.00, "8-25-19", "8-26-19", "Test", file, 1, 1, 1, 1);
		reim.setStatus(rLogic.statusToEnum(reim.getStatusNum()));
		reim.setType(rLogic.typeToEnum(reim.getTypeNum()));
		TreeMap<Integer, Reimbursement> reims = new TreeMap<>();
		reims.put(1, reim);
		reims.put(2, reim);
		Reimbursement reim2 = new Reimbursement(2, 25.00, "8-25-19", "8-26-19", "Test", file, 2, 2, 2, 2);
		reim.setStatus(rLogic.statusToEnum(reim.getStatusNum()));
		reim.setType(rLogic.typeToEnum(reim.getTypeNum()));
		Reimbursement reim3 = new Reimbursement(3, 25.00, "8-25-19", "8-26-19", "Test", file, 3, 3, 3, 3);
		reim.setStatus(rLogic.statusToEnum(reim.getStatusNum()));
		reim.setType(rLogic.typeToEnum(reim.getTypeNum()));
		reims.put(3, reim2);
		reims.put(4, reim3);
		
		when(rDAOMock.getAllReimbursements()).thenReturn(reims);
		assertEquals(reims, rLogic.grabAllCompleteReimbursements());
	}
/*
 * **********************************************UpdateStatus*************************************************
 */
	@Test
	public void testUpdateStatus() {
		
		byte[] file = {1,2,3,4};
		Reimbursement reim = new Reimbursement(1, 25.00, "8-25-19", "8-26-19", "Test", file, 1, 1, 1, 1);
		reim.setStatus(rLogic.statusToEnum(reim.getStatusNum()));
		reim.setType(rLogic.typeToEnum(reim.getTypeNum()));
		
		when(rDAOMock.getReimbursementFromReimId(1)).thenReturn(reim);
		when(rDAOMock.updateReimbursementStatus(reim)).thenReturn(true);
		assertEquals(true, rLogic.updateStatus(1, 1, 1));
	}

}
