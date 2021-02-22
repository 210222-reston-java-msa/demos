package com.revature.threads;

import java.util.ArrayList;
import java.util.List;

interface StringSmush {
	
	public String smushed(String a, String b);
}

public class ThreadWithLambda {

	/*
	 * Lambda Expressions were introduced in Java 8.
	 * 
	 * A lambda is a short block of code which takes in parameters and returns a
	 * value.
	 * 
	 * Lambda expressions are similar to methods, but they do not need a name and
	 * can be implemented in the body of a method.
	 */

	public static void main(String[] args) {
		
		// Like we did with StringSmush (custom) interface, we're going to use Lambda
		// Expressions to create a Runnable object...
		
		Runnable r = () -> {
			
			System.out.println("I'm a thread created with a Lambda expression!");
			
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("The lambda runnable has ended");
		};
		
		Thread thread = new Thread(r);
		thread.start(); // start() calls the run() method...which we've defined in a lambda ^ 
		
		

		StringSmush s = (a, b) -> a + "is smushed together with" + b;
		
		System.out.println(s.smushed("Random", "Word"));
		
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		// 1. I'll add some numbers to this list
		numbers.add(2);
		numbers.add(9);
		numbers.add(11);
		numbers.add(15);

		// 2. I'm going to create a Lambda expression to print out each number
		numbers.forEach((n) -> {
			System.out.println("The number is " + n);
		});

		// Lambdas allow us to pass a method as a parameter. Lambdas allow us to use a
		// method without giving it a name

	}

}
