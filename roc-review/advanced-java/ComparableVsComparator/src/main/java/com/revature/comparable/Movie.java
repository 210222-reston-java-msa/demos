package com.revature.comparable;

import java.util.Comparator;

// We want to make movies a comparable object
// So, we must implement the Comparable interface

// A comparable object is capable of comparing itself with another object
public class Movie implements Comparable<Movie>{
	
	private double rating;
	private String name;
	private int year;
	
	public Movie(double rating, String name, int year) {
		super();
		this.rating = rating;
		this.name = name;
		this.year = year;
	}


	// we want to sort movies by year (of release)
	// This is what I've declared as my natural mechanism
	public int compareTo(Movie m) {
		
		return m.year - this.year; // this sorts it in desc order
	}
	

	public double getRating() { return rating; }


	public void setRating(double rating) {
		this.rating = rating;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}

}
