package com.revature.predicate;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class PredicateDriver {
/*
 * Java 8 grants us the power of a couple built in functional interfaces.
 * 1. Predicate ->  returns a boolean
 * 2. Consumer
 * 3. Supplier
 */
	
	public static void main(String[] args) {
		
		predicateExample();
		consumerExample();
	}
	
	public int sum(int x, int y) {
		return x + y; // (returning something!)
	}
	
	public static void predicateExample() {
		// returns a boolean value based on a (lambda) expression
		Predicate<Integer> lessThanTen = n -> (n < 10); // will return true or false
		Predicate<Integer> greaterThanTen = n -> (n > 10);
		
		// use the .test() method on the Predicate
		System.out.println("Is it less than 10?");
		System.out.println("5 " + lessThanTen.test(5));
		System.out.println("10 " + lessThanTen.test(10));
		
		System.out.println("Is it greater than 10?");
		System.out.println("5 " + greaterThanTen.test(5));
		System.out.println("100 " + greaterThanTen.test(100));
		
		// you can also use .or to combine predicates (or other logical operators)
		System.out.println("Is it less than OR greater than 10?");
		System.out.println("5 " + lessThanTen.or(greaterThanTen).test(5));
		System.out.println("10 " + lessThanTen.or(greaterThanTen).test(10));
		System.out.println("20 " + lessThanTen.or(greaterThanTen).test(20));
	}

	/*
	 * Consumer is another functional interface that Java 8 gives us
	 * Consumer takes in a single input and doesn't return anything...
	 */
	public static void consumerExample() {
		// 1. Create a Predicate that tests for an Odd number
		// 2. Create a Predicate that tests for an Even number
		Predicate<Integer> oddNumber = n -> (n % 2 != 0);
		Predicate<Integer> evenNumber = n -> (n % 2 == 0);
		
		
		// Next create an int[] called numbers and throw a bunch of different numbers in it
		Integer[] numbers = {1, 5, 10, 2, 9, 400, 402, 80, 89, 33, 92};
		
		// Use a Stream to process the collection of integers, and filter them.
		// a Stream is a sequence of OBJECTS that can be processed with special methods (like filter).
		// Stream API
		Stream<Integer> odds = Arrays.stream(numbers).filter(oddNumber);
		Stream<Integer> evens = Arrays.stream(numbers).filter(evenNumber);
		/*
		 * filter() takes a predicate as an argument which determines how we
		 * should process our objects (in the stream);
		 */
		
		// we will use a Consumer to print out all the odds and evens
		Consumer<Integer> printOut = (i) -> System.out.println(i); 		
		
		// use our consumer to printout all odds and all evens....
		// Use the streams!!!
		
		System.out.println("ODDS: ");
		odds.forEach(printOut);  // we are performing the action for each element of the Stream
		
		System.out.println("EVENS: ");
		evens.forEach(printOut);
		
	}
	
	class Employee {
		// fields
		private int timeAtCompany;
		private int workScore;
		
		public int getTimeAtCompany() {
			return timeAtCompany;
		}
		public void setTimeAtCompany(int timeAtCompany) {
			this.timeAtCompany = timeAtCompany;
		}
		public int getWorkScore() {
			return workScore;
		}
		public void setWorkScore(int workScore) {
			this.workScore = workScore;
		}

	}
	
	// this is a class of methods to return a Predicate
	class EmployeePredicates {
		
		// this is a method that returns a Predicate to evaluate an Employee
		public Predicate<Employee> isReadyToBePromoted() {  
			return e -> e.getTimeAtCompany() > 1 && e.getWorkScore() > 80;
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
