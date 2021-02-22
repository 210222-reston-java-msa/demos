package com.revature;

import java.util.Scanner;

import com.revature.models.Student;


public class Driver {

	public static Scanner scan = new Scanner(System.in);
	
	
	
	public static void main(String[] args) {
		
		// 1. Ask the user how many students we want to add to the DB
		System.out.println("Enter number of new students to enroll:");
		
		// user enters a number
		int numOfStudents = scan.nextInt(); // 2
		
//		String[] words = new String[3]; // [__, __, __]
	
		Student[] arrOfStudents = new Student[numOfStudents]; // [___, ___]
		
//		int[] numbers = new int[3]; // [_0_, _0_, _0_]
//		
//		numbers[0] = 100; // [100, _0_, _0_]
//		numbers[2] = 3000; // [100, _0_, 3000]
		
		
		// 2. Create n number of students
		for (int i=0; i < numOfStudents; i++ ) {
			
			arrOfStudents[i] = new Student();
			arrOfStudents[i].enroll();
			arrOfStudents[i].payTuition();
			System.out.println("==========================================");
			
		}
		
		// 3. print them to the console
		for (Student s : arrOfStudents) {
			System.out.println(s);
		}

	}

	
	
	
	
	
	
	
	
}
