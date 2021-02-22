package com.revature.comparable;

import java.util.Comparator;

public class MovieComparators {

}

// Comparators 

class CompareRating implements Comparator<Movie>{

	public int compare(Movie m1, Movie m2) { // [__, __, __, __]
		
		if (m1.getRating() < m2.getRating()) return -1;
		if (m1.getRating() > m2.getRating()) return 1;
		else return 0;
	}

}

class CompareNames implements Comparator<Movie> {

	public int compare(Movie m1, Movie m2) {
		return (m1.getName().compareTo(m2.getName()));
	}
	
}
