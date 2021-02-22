package com.revature.controlflow;

import java.util.Scanner;

public class GradingCalc {

	public static void main(String[] args) {
	
		// This is the basic version:
		
		
//		Scanner scan = new Scanner(System.in);
		
//		System.out.println("Please enter the student's number grade out of 100: ");
//		
//		// now i assign the value that I capture to a reference variable
//		int score = scan.nextInt();
//		
//		if (score >= 90) {
//			System.out.println("The student receives an A! (" + score + "%)");
//		} else if (score >= 80) {
//			System.out.println("The student receives an B! (" + score + "%)");
//		} else if (score >= 70) {
//			System.out.println("The student receives an C! (" + score + "%)");
//		} else if (score >= 60) {
//			System.out.println("The student receives an D! (" + score + "%)");
//		} else {
//			System.out.println("The student receives an F! (" + score + "%)");
//		}
		
		// This is a change
		
		Scanner scan = new Scanner(System.in);
	
		System.out.println("Please enter the maximum grade amount:");
		double maxScore = scan.nextDouble();
		System.out.println("Please enter the student's number grade out of " + maxScore);
		double score = scan.nextDouble();
		
		double percent = (score/maxScore) * 100; // we have to change this to double, otherwise
		
		if (percent >= 90) {
		System.out.println("The student receives an A! (" + percent + "%)");
	} else if (percent >= 80) {
		System.out.println("The student receives an B! (" + percent + "%)");
	} else if (percent >= 70) {
		System.out.println("The student receives an C! (" + percent + "%)");
	} else if (percent >= 60) {
		System.out.println("The student receives an D! (" + percent + "%)");
	} else {
		System.out.println("The student receives an F! (" + percent + "%)");
	}
		

	}
	
	/*
	 * The GradingCalc does the following: 
	 * ** extra first prompt for the total score ---
	 * 1. It prompts the user to enter score of a student's quiz 
	 * 
	 * 2. Upon entering the score, the application will calculate the letter grade that the 
	 * student has received
	 * 
	 * 75/100 --> " The student receives a C (75%)"
	 * 
	 * A 90 -100
	 * B 80 -89
	 * C 70-79
	 * D 60-69
	 * F 0-59
	 * 
	 * Advanced Challenge: -->
	 * 	 create a program that asks for a total score (for example, a quiz might be scored out
	 * of 20, not 100 
	 * */

}
