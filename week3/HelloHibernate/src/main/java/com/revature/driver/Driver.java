package com.revature.driver;

import com.revature.dao.CrimeDao;
import com.revature.models.Crime;

public class Driver {

	public static void main(String[] args) {

			initialValues();
	}
	
	
	public static void initialValues() {
		
		// Create new Crime objs
		Crime c1 = new Crime("Arson", "setting something ablaze");
		Crime c2 = new Crime("Freeze", "covering a whole city in ice");
		Crime c3 = new Crime("Time Manipulation", "freeze time, rob banks");
		
		// create a crime dao
		CrimeDao cDao = new CrimeDao();
		
		// use the crimeDao to insert the crimes
		cDao.insert(c1);
		cDao.insert(c2);
		cDao.insert(c3);
		
		// build a list of these crimes
		
		
		// I'll instantiate some super villains
	}

}
