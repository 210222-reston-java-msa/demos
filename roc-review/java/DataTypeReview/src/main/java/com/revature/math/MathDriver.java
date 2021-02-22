package com.revature.math;

import java.util.Random;
import java.util.Scanner;

public class MathDriver {
// Here we'll be using the Math Class
	/*
	 * Math extends Object (like String)
	 * 
	 * The class Math contains methods for performing basic numeric operations such
	 * as the elementary exponential, logarithm, square root, and trigonometric
	 * functions.
	 */

	public static void main(String[] args) {

//		System.out.println("The random number from getRandomNumber() is: " + getRandomNumber());
//		System.out.println("The random number from getRandomWholeNumber() is: " + getRandomWholeNumber());
//		System.out.println("The random number from getRangedRandomWholeNumber() is: " + getRangedRandomNumber(2, 6));
//
//		System.out.println("The random number from generateRandomClassInt is: " + generateRandomClassInt(1000));

		guessingGame();

	}

	/*
	 * Let's create some methods to generate random numbers
	 * 
	 * 1. use java.lang.Math (it extends the Object class) 2. use java.util.Random*
	 * (More on this later)
	 */

	public static double getRandomNumber() {
		double x = Math.random();

		return x; // returns > 0.0 && < 1.0
	}

	public static int getRandomWholeNumber() {

		double x = (Math.random() * 10); // think 0.79782738 * 10 = 7.982738
		int y = (int) x;

		return y + 1; // adding 1 to what we return will make it ALWAYS between 1 - 10;

	}

	// 2 - 15
	public static int getRangedRandomNumber(int min, int max) {

		int x = (int) (Math.random() * ((max - min) + 1) + min);

		return x;

	}

	public static int generateRandomClassInt(int upperRange) {

		// Random comes from java.util!!!
		Random random = new Random();
		int x = random.nextInt(upperRange);

		return x;

	}

	public static void guessingGame() {
		// 1. prompt the user to guess a number
		System.out.println("Guess a number between 1 - 10!:");
		
		// 2. Create a scanner object and take in that number
		Scanner scan = new Scanner(System.in);
		
		// 3.  generate a random number
		// use the method we already created called generateRandomWholeNumber;
		int correctAnswer = getRandomWholeNumber(); // this generates a number between 1 - 10
		
		// 4. check to see if the user's input is the same as the generated number
		
		// IF it's less than, print "Sorry too low"
		
		int count = 0;
		
		do {
			int userInput = scan.nextInt();
			
			if (userInput < correctAnswer) {
				System.out.println("Sorry too low");
			} else if (userInput > correctAnswer) {
				System.out.println("Sorry too high");
			} else {
				System.out.println("Congrats you got it!");
				break;
			}
			
			count++;
			
		} while (count < 3);
		
		System.out.println("Game over");
		
	}

}
