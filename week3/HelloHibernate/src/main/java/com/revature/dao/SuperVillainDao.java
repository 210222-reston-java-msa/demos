package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.revature.models.SuperVillain;
import com.revature.util.HibernateUtil;

/*
 * 3 different ways to write complex queries in Hibernate:
 * 
 * 1. HQL - Hibernate Query Language
 * 2. Criteria API
 * 3. Native SQL
 *  
 */
public class SuperVillainDao {
	
	public SuperVillainDao() {
		
	}
	
	public void insert(SuperVillain villain) {
		
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		ses.save(villain);
		
		tx.commit();
		
	}
	
	public void update(SuperVillain villain) {
		
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		ses.update(villain);
		tx.commit();
		
	}
	
	public SuperVillain selectById(int id) {
		
		Session ses = HibernateUtil.getSession();
		
		SuperVillain vill = ses.get(SuperVillain.class, id); // we can only use get if we're querying by the PK/ID
		
		return vill;
	}
	
	public SuperVillain selectByName(String name) {
		// By default Hibernate protects against SQL Injection
		
		// we always need a Sesison object to perform a transaction against the DB
		Session ses = HibernateUtil.getSession();
		
		// HQL --> Hibernate Query Language
		// creates a complex query by using a combo of native SQL and OOP
		// HQL targets Java Objects, NOT SQL tables
		List<SuperVillain> villList = ses.createQuery("from SuperVillain where name='"+name+"'", SuperVillain.class).list();
		
		
		// NATIVE SQL
		// Create complex queries using plain old SQL
		// Native SQL targets SQL tables, NOT Java Objects
		//List<SuperVillain> villList = ses.createNativeQuery("SELECT * FROM Super_Villain WHERE name = '"+name+"'", SuperVillain.class).list();
		
		
		// Criteria API
		// Creates complex queries programatically.  it only uses OOP
		// Criteria API targets Java Objects
		// List<SuperVillain> villList = ses.createCriteria(SuperVillain.class).add(Restrictions.like("name", name)).list();
		
		
		if(villList.size() == 0) {
			System.out.println("PANIC -- NO VILLAIN FOUND WITH THAT NAME");
			return null;
		}
		
		return villList.get(0);
		
	}
	
	public List<SuperVillain> selectAll() {
		
		// Let's use HQL
		Session ses =  HibernateUtil.getSession();
		
		List<SuperVillain> villList = ses.createQuery("from SuperVillain", SuperVillain.class).list();
		// HQL is returning all instances of the SuperVillain class
		
		// Notice that we don't need a Transaction Object here...
		// We are just QUERYING data, not inserting or updateing the DB, hence no changes are made to the DB, it's NOT a transaction...
		
		return villList;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
