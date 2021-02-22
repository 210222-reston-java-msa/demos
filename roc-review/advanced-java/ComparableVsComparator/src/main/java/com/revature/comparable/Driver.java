package com.revature.comparable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class Driver {
	
	/*
	 * Java provides 2 interfaces to sort objects using data members of the class
	 * 
	 * 1. Comparable interface
	 * 2. Comparator interface
	 */
	
	public static void main(String[] args) {
		
		ArrayList<Movie> movies = new ArrayList<Movie>();
		
		movies.add(new Movie(8.1, "Soul", 2020));
		movies.add(new Movie(8.5, "Grave of the Fireflies", 1988));
		movies.add(new Movie(8.3, "Spider-Man: Into the Spider-Verse", 2018));
		movies.add(new Movie (2.0, "Disaster Movie", 2008));
		
		Collections.sort(movies); // here we are deferring to the compareTo() method
		// compareto() is implemented by the comparable interface
		
		System.out.println("Movies after sorting with compareTo()");
		// 1. create an enhance for-loop and print the newly sorted list (with each element on a line)
		for (Movie m : movies) {
			System.out.println(m.getName() + " " + m.getRating() + " " + m.getYear());
		}

		
		CompareRating rateCompare = new CompareRating();
		Collections.sort(movies, rateCompare); // we are passing an object that specifies HOW to compare
		// we pass through an object of type comparator...

		System.out.println("Movies after sorting by rating (this is a custom class we created)");
		
		for (Movie m : movies) {
			System.out.println(m.getName() + " " + m.getRating() + " " + m.getYear());
		}
		
		// building a comparator with a lambda expression
		Comparator<Movie> c = (Movie m1, Movie m2) -> {
			
			if (m1.getRating() < m2.getRating()) return 1; // this is descending order
			if (m1.getRating() > m2.getRating()) return -1;
			else return 0;
			
		};
		
		Set<Movie> movieSet = new TreeSet<Movie>(c); // this will, by default sort all added Movies according
		// to our comparator defined as "c"
		
		movieSet.add(new Movie(8.1, "Soul", 2020));
		movieSet.add(new Movie(8.5, "Grave of the Fireflies", 1988));
		movieSet.add(new Movie(8.3, "Spider-Man: Into the Spider-Verse", 2018));
		movieSet.add(new Movie (2.0, "Disaster Movie", 2008));
		
		System.out.println("*******************************************************");
		
		for (Movie m : movieSet) {
			System.out.println(m.getRating());
		}
		
	}

}
