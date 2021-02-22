package com.revature.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class LambdaDriver {
/*
 * Imperative (Procedural) Languages like any OOPS languages (C#, Java, C++) tell the computer
 * how to change buts, bytes, and words in its memory (int x = 10;...then do something with x).
 * 
 * In functional (declarative) programming, we tell the computer WHAT things, actions, etc. are.
 * Functional programming involves composing the problem as a set of functions to be executed.
 * You define the input to each function and what the function RETURNS.
 */
	public static void main(String[] args) {
		
		verboseWay();
		lambdaWay();

	}
	
	// Verbose code is "wordy". A lot of lines of code
	public static void verboseWay() {
		
		// We're just overriding the abstract method of the interface within this method
		MyFunctionalInterface mfi = new MyFunctionalInterface() {

			@Override
			public void printSomething() {
				System.out.println("done, but syntax is verbose");
			}
		};
		
		// this will return something when we call the verboseWay() method.
		mfi.printSomething();
	}
	
	public static void lambdaWay() {
		// A lambda is nothing more that (parameter1, paramter2) -> expression
		
		// 								 (parameter1, parameter2) -> { code block }
		MyFunctionalInterface mfi = () ->  {
			System.out.println("...with lambda!");
		};
		mfi.printSomething();
	}
	
	Predicate<Integer> isPrime = n -> {
		
		int count=0;
		
		for (int i=2;i<=(n/2)+1;i++)
			
			if (n%i==0) count++;
		
		if (count>=1) return false;

		return true;
	};


	
//	public static void something() {
//		Predicate<Integer> myPred = () -> {
//			// this won't work becatse Predicate's test() function doesn't like it
//		};
//	}
//	
	/*
	 * Java 8 provides us built-in functional interfaces such as Consumer,
	 * Predicate, Supplier.
	 */
	public static void forEachWithLambda() {
		// 1. create an arraylist of strings
		List<String> list = new ArrayList<String>();
		// 2. add some strings to it
		list.add("spaghetti");
		list.add("bread");
		list.add("cheese");
		// 3. implement forEach() and use a lambda expression to define
		// what operation we want to perform on each string in the list
		
		// we can supply a lambda expression in the forEach loop to define the functionality
		// of the Consumer functional interface that forEach takes as an argument
		list.forEach(s -> System.out.println(s));
		// provide a lambda expression to print each String s to the screen
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}








