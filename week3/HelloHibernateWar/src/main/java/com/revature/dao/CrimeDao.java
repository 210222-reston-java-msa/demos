package com.revature.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Crime;
import com.revature.util.HibernateUtil;

/*
 * Session methods:
 * 
 * save() and persist() --------result in a SQL insert
 * update() and merge() --------result in a SQL update
 * saveOrUpdate() --------------result in a SQL insert OR update (depends)
 * get() and load() ------------results ina SQL select
 * 
 */

public class CrimeDao {
	
	// we will just create an insert method for Crime
	public void insert(Crime crime) {
		
		Session ses = HibernateUtil.getSession(); // 1. capture the session
		
		// The Transaction Interface is used for managing ACID complient transactions against the DB
		// Transaction is a mechanism for dispatching SQL statements against the DB
		
		Transaction tx = ses.beginTransaction();  // 2. Perform an operation on the DB
		
		ses.save(crime); // 3. use the save() session method to perform an insert operation
		
		tx.commit(); // 4. commit the transaction by utilizing a method from the actual Transaction interface;
	}
	

}
