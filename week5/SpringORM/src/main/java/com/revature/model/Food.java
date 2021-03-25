package com.revature.model;

import javax.persistence.*;

@Entity
@Table(name="FOOD") // this is a POJO
public class Food {

	@Id
	@Column(name="food_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int foodId;
	
	@Column(name="dish_name", unique=true, nullable=false)
	private String dishName;
	
	@Column(name="calories", nullable=false)
	private double calories;

	
	public Food() {}
	
	public Food(String dishName, double calories) {
		super();
		this.dishName = dishName;
		this.calories = calories;
	}

	public Food(int foodId, String dishName, double calories) {
		super();
		this.foodId = foodId;
		this.dishName = dishName;
		this.calories = calories;
	}

	@Override
	public String toString() {
		return "Food [foodId=" + foodId + ", dishName=" + dishName + ", calories=" + calories + "]";
	}

	public int getFoodId() {
		return foodId;
	}

	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public double getCalories() {
		return calories;
	}

	public void setCalories(double calories) {
		this.calories = calories;
	}
	
	
	
	
	
	
}