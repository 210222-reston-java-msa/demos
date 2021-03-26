package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.model.Food;
import com.revature.repository.FoodRepo;

@Controller
@RequestMapping("/food")
@CrossOrigin(origins="http://localhost:4200")
public class FoodController {
	
	/*
	 * Before DI
	 * 
	 * private FoodRepo foodRepo = new FoodRepository();
	 */
	
	// instead we use the power of automatic dependency injection
	@Autowired
	private FoodRepo foodRepo;
	
	
	// In my web handling methods I need call on my FoodRepo!
	// Therefore the FoodController has a dependency.....
	@GetMapping("/all")
	public @ResponseBody List<Food> findAllFoods() {
	
		return (List<Food>) foodRepo.findAll();
		// because I extended the CrudRepository class from Spring Data, I have
		// access to some built in DAO methods
		
		// Spring Data creates my DAO for me	
		
		
		// What if I wanted to return all of the food objects in order of DishName?
		// return (List<Food>) foodRepo.findByOrderByDishName();
	}
	
	@GetMapping("/pizza")
	public @ResponseBody Food findFood() {
		return foodRepo.findByDishName("pizza");
		// this will return a food object with the dishName matching "pizza"
	}
	
	
	// Post mapping at "/add" to INSERT a food object -> reutnr the string "success"
	@PostMapping("/add")
	public @ResponseBody String addFood(@RequestBody Food f) {
		foodRepo.save(f);
		return "Success";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
