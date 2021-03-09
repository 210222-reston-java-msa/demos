package com.revature.driver;

import java.util.ArrayList;
import java.util.List;

import com.revature.dao.CrimeDao;
import com.revature.dao.SuperPrisonDao;
import com.revature.dao.SuperVillainDao;
import com.revature.models.Crime;
import com.revature.models.SuperPrison;
import com.revature.models.SuperVillain;
import com.revature.util.HibernateUtil;

public class Driver {

	public static void main(String[] args) {

			initialValues();
			
			// runa selectAll statement from one of our Daos.....
			SuperPrisonDao spdao = new SuperPrisonDao();
			
			System.out.println(spdao.selectAll());
			
			// close the Hibernate Session here;
			HibernateUtil.closeSes();
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
		List<Crime> crimes = new ArrayList<Crime>();
		crimes.add(c1);
		crimes.add(c2);
		crimes.add(c3);
		
		// Create superPrison
		SuperPrison sp1 = new SuperPrison("Azkaban", "England");
		
		// I'll instantiate some super villains
		SuperVillain sv1 = new SuperVillain("Joker", "Evilness", 100000, crimes, sp1);
		
		// Create SuperVillainDao
		SuperVillainDao svdao = new SuperVillainDao();
		svdao.insert(sv1);
		
		
		// Create a SuperPrisonDao here
		SuperPrisonDao spdao = new SuperPrisonDao();

		// create a list of all villains
		List<SuperVillain> villains = new ArrayList<SuperVillain>();
		villains.add(sv1);
		
		// send the list of villains to the prison 		
		sp1.setVillList(villains);
		
		// inser the prison into the DB.
		spdao.insert(sp1);

	}

}




















