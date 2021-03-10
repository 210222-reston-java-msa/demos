package com.revature.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	/*
	 * Creating the HibernateUtil.java Helper File
	 * 
	 * To use Hibernate, you need to create a helper class that handles startup
	 * and access Hibernate's SessionFactory to obtain a Session object
	 * 
	 * 
	 * (interface)
	 * Session manages the connection to DB and provides CRUD operations (create, read, update, delete)
	 * 
	 * (class)
	 * Configuration's job is to gather info from the hiberate.cfg.xml file and then build the Session Factory.
	 * 
	 * (interface)
	 * Session Factory's job is to create sessions and store info on how
	 * to make connections to your DB
	 * 
	 * (interface) 
	 * Transaction manages..well...your transactions and cache (must be
	 * ACID).
	 * 
	 * 
	 * Query is used to write complex CRUD operations using HQL (Hibernate Query
	 * Language) 
	 * 
	 * Criteria is for programmatically writing Select queries
	 */
	
	private static Session ses;
	
	// We will use the SessionFactory interface to create a Configuration()
	// Object which will call the .configure method on on our "hibernate.cfg.xml"
	private static SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	
	// We create a getSession() method which is going to be called in our DAO layer
	// This method obtains a JDBC connection which we can use to perform a 
	// transaction against our DB
	public static Session getSession() {
		
		if (ses == null) {
			ses = sf.openSession();
		}
		
		return ses;
		
	}
	
	// This closes an active session.
	public static void closeSes() {
		ses.close();
		sf.close();
		
		// By closing our session, you free up the connections from the conn pool
		// so that it can be used by a new session.
	}	

}
