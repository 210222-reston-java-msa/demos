package com.revature.driver;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.revature.dao.CrimeDao;
import com.revature.dao.SuperPrisonDao;
import com.revature.dao.SuperVillainDao;
import com.revature.models.Crime;
import com.revature.models.SuperPrison;
import com.revature.models.SuperVillain;
import com.revature.util.HibernateUtil;

public class Driver {

	/*
	 * What are the benefits of Hibernate? 
	 * 			-- it's fast 
	 * 			-- it's object oriented 
	 * 			-- it uses Caching...(there are connection pool which minimize the bottle necked
	 * 				speed caused by forming DB connections) -- it's modular -- this means that we
	 * 				can change the type of DB we connect to by altering the hibernate.cfg.xml file
	 * 
	*/

	public static void main(String[] args) {

//		initialValues();

//		firstLevelCaching();
		
		secondLevelCaching();
		
		// close the Hibernate Session here;
		HibernateUtil.closeSes();

	}
	
	// -- L1 caching or Session-Level Caching
	public static void firstLevelCaching() {
		/*
		 * By default, Hibernate allows first level caching...
		 * 
		 * This means that any data that a particular session retrieves, is stored
		 * and accessible within the context of that particualr session (and no other session)
		 * 
		 */
		
		Session ses1 = HibernateUtil.getSession();
		
		// I will use the session get() or load() methods to return an entity (a mapped object) from the DB
		SuperVillain vill1 = ses1.load(SuperVillain.class, 4);
		
		// the Session is making a call to the db and returning this object
		System.out.println("First call output: " + vill1.getName());
		
		// this particular object is already in our cache, the session does NOT fire a call to the Database
		// Instead it loads what we have in our session's cache
		SuperVillain vill2 = ses1.load(SuperVillain.class, 4);  // it doesn't need to make the trip to the DB for the same obj
		System.out.println("Second call output: " + vill2.getName());
		
		// See here that there's only 1 entity (Joker) that we're retrieving from our session-level cache
		System.out.println("The amount of entities in our session is " + ses1.getStatistics().getEntityCount()); // this returns 1....
		
		// Where is the cache?
		// Both first and second level cache is stored on the heap (created by the JVM...where all objects live)
		// which is why it's so fast to retrieve an object within them
		
	}
	
	
	public static void secondLevelCaching() {
		/*
		 * SECOND LEVEL CACHING
		 * 
		 * By default, Hibernate disables this....
		 * The developer is needs to enable it explicitly, and the SessionFactory object is responsible
		 * for maintaining it.
		 * 
		 */
		
		// open 2 different sessions
		Session ses1 = HibernateUtil.getSession();
		
		
		// When we load one particular enitty from the DB with our first session --
		// this object is accessible by ALL sessions (because it's accessible at the SessionFactory scope a.k.a second level)
		SuperVillain vill1 = ses1.load(SuperVillain.class, 4); // here we get it from the DB and put it in the cache
		SuperVillain vill2 = ses1.load(SuperVillain.class, 4);  // here we check for it FIRST if it's in the cache...it is, so we just return it
		// instead of going back to the DB and getting another Entity\
		System.out.println(vill1.getName());
		
		System.out.println(ses1.getStatistics().getEntityCount()); // this will return 1...
		/*
		 * There are some handy methods we can apply to sessions:
		 * 
		 * 		.evict() -- this clears all of the entities from the particular session itself (on the session level) 
		 * 		.clear()
		 * 		.refresh()
		 */
		ses1.evict(vill1); // this will kick the entity OUT of the session's cache... now if the session wants it, 
		// it can get it from the second-level cache that we've enabled, before going to the DB
		
		// seeing how many entities still exist on the session...
		System.out.println(ses1.getStatistics().getEntityCount());  
		
		// Fetching the same entity again, using the same session 
		
		System.out.println(vill1.getName()); // this is retrieved from the second level, since it doens't exist on the first anymore
		
				
		System.out.println("Session 1 Entity count " + ses1.getStatistics().getEntityCount());		
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

		// insert the prison into the DB.
		spdao.insert(sp1);

	}

}
