package com.revature.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.revature.model.Food;
// This is a FOOD repo so only food allowed ! >:o
// CrusRepository allows us to specify methods pertinent to only 1 type of object.
public interface FoodRepo extends CrudRepository<Food, Integer>{ // the integer represents the PK of Food

	// This is a Property Expression
	// Property expressions infer SQL statements on entities based on 
	// the DIRECT properties of the entity being managed.
	public Food findByDishName(String dishName);
	
	public Food findByDishNameIgnoreCaseAndCalories(String dishName, double calories);
	
	public List<Food> findByOrderByDishName(); // this will automatically return all of my
											// Food entities in ASC order by their names.
	
	// basic CRUD methods like save(), get, etc are abstracted away, but still exist.
	
}
