package com.revature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 * What is Project Lambok?
 * 
 * Lambok is a java library that automatically plugs
 * into your editor (IDE).  It allows you to generate methods and
 * constructors using JUST annotations. @ToString, @Gette
 */

@Entity
@Table(name="FOOD")
public class Food {
	
	@Id
	@Column(name="food_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int foodId;
	
	@Column(name="dish_name", unique=true)
	private String dishName;
	
	@Column(name="calories")
	private double calories;
	
	
	public Food() {}

	public Food(int foodId, String dishName, double calories) {
		super();
		this.foodId = foodId;
		this.dishName = dishName;
		this.calories = calories;
	}

	public Food(String dishName, double calories) {
		super();
		this.dishName = dishName;
		this.calories = calories;
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

	@Override
	public String toString() {
		return "Food [foodId=" + foodId + ", dishName=" + dishName + ", calories=" + calories + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(calories);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((dishName == null) ? 0 : dishName.hashCode());
		result = prime * result + foodId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Food other = (Food) obj;
		if (Double.doubleToLongBits(calories) != Double.doubleToLongBits(other.calories))
			return false;
		if (dishName == null) {
			if (other.dishName != null)
				return false;
		} else if (!dishName.equals(other.dishName))
			return false;
		if (foodId != other.foodId)
			return false;
		return true;
	}
	
	// getters & setters
	
	
	
	// to String
	
	

}
