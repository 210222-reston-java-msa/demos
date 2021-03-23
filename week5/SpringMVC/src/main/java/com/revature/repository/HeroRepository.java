package com.revature.repository;

import java.util.List;

import com.revature.model.Hero;

public interface HeroRepository {
	
	// Repository is only for an object of a specific type
	void save (Hero hero);
	
	List<Hero> findAll();
	
	Hero findByName(String name);

}
