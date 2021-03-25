package com.revature.repository;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.model.Food;

/*
 * StereoType anotationss indicate the roles or types of methods in the overall architecture of the class
 * 
 * - Controller (handles web requests)
 * - Repository (handle CRUD operations against a DB)
 * - Service (handle business logic in conjunction with a persistance layer)
 * - Component (signals that the class is a parent) 
 * 
 */

@Repository("foodRepo") // this means we'll most likely have CRUD methods
@Transactional // specifies that any methods made within this class are inherantly acidic.
public class FoodRepo {
	
	// Since we declared this property as autowired, our IoC Container (defined by our application context) 
			// will automatically inject this dependency into an instance of this class
	@Autowired 
	private SessionFactory sesFact; // this bean will be decalred in our applicationContext
	
	
	public FoodRepo() {}
	
	
	public void insert(Food food) {    // food.dishName = pasta, food.calories = 100;
		// The old way (with just Hibernate)
//		Session ses = sesFact.openSession();
//		Transaction tx = ses.beginTransaction();
//		
//		ses.save(food); // INSERT INTO FOOD (dish_name, calories) VALUES (?, ?)
//		
//		tx.commit();
//		
//		ses.close();
		
		sesFact.getCurrentSession().save(food); // this returns number
		
	}
	
	public void update(Food food) {
		
		// - in DB: 3, "cake", 400 
		// to update this to 500 calories food == {3, "cake", 500 }
		try {
		sesFact.getCurrentSession().update(food);
		
		} catch (EntityNotFoundException e) {
			
			System.out.println(e);
		}
	}
	
	
	public List<Food> selectAll() {
										 // THIS IS HQL	// SELECT * 
		List<Food> foodList = sesFact.getCurrentSession().createQuery("from Food").list();
		
		return foodList;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
