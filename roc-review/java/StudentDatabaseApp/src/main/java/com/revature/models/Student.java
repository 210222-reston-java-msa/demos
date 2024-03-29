package com.revature.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.exceptions.NotAGradeException;

// ctrl + shift + s to save all
// ctrl + s to save 1
public class Student {
	
	private static Scanner scan = new Scanner(System.in);
	
	// Student instance variables (properties or fields)
	private String firstName;
	private String lastName;
	private int gradeYear; // 1 = freshman, 2 = sophomore, etc...
	private String studentId;
//	private String courses = ""; covert to ArrayList
	private List<Course> courses = new ArrayList<Course>();
	private int tuitionBalance = 0;
	
//	private static int costOfCourse = 600;
	// later, let's create a static ID of 1000 which increments everytime we add a new student
	
	private static int id = 1000; // everytime a student object is instantiated (created)
	// we increment this id by 1
	
	
	// Constructor: prompt the user to enter a student's name and year
//	public Student() {
////		Scanner scan = new Scanner(System.in);
//		
//		// capture the first name
//		System.out.println("Enter the student's first name");
//		this.firstName = scan.next();
//		
//		// capture the second name
//		System.out.println("Enter the student's last name");
//		this.lastName = scan.next();
//		
//		// capture the gradeyear -- here we're using escape sequences
//		System.out.println("Enter Student grade year:\n1 - Freshman\n2 - Sophomore\n3 - Junior\n4 - Senior");
//		this.gradeYear = scan.nextInt();
//		
//		this.courses = new ArrayList<Course>();
//		// when a student is instantiated, they get their own unique id AND it increments the static id of the class
//		setStudentId();
//		
//		System.out.println(firstName + " " + lastName + " " + gradeYear + " " + studentId);
//	
//	}
//	
	
	
	
//
//	public Student(String firstName, String lastName, int gradeYear, String studentId, List<Course> courses,
//			int tuitionBalance) {
//		super();
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.gradeYear = gradeYear;
//		this.studentId = studentId;
//		this.courses = courses;
//		this.tuitionBalance = tuitionBalance;
//		
//		// when a student is instantiated, they get their own unique id AND it increments the static id of the class
//		setStudentId();
//	}

	public Student(String firstName, String lastName, int gradeYear) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gradeYear = gradeYear;

		setStudentId();
		
		System.out.println(firstName + " " + lastName + " " + gradeYear + " " + studentId);
		
	}

	
	// generateId() method
	public void setStudentId() {
		// Grade level + id
		id++;
		this.studentId = gradeYear + "" + id; 
	}
	
	
	// enroll() method
	public void enroll() {
		
		List<Course> coursesToAdd = new ArrayList<Course>();
		
		// Get inside a loop, then the user will hit 'Q' for quit to quit the program
		
		boolean isDone = false;
		
		while (!isDone) {
			
			System.out.println("Enter a course to enroll. (Q to quit)");

			String courseName = scan.next();


			if (!courseName.equalsIgnoreCase("Q")) {

				Course newCourse = new Course(courseName);
				
				coursesToAdd.add(newCourse);
				tuitionBalance += Course.costOfCourse;
				
				scan.nextLine();

			} else {

				System.out.println("======== breaking =======");
				isDone = true;
				
				
			}
			
		}
		
		courses = coursesToAdd;
		
		System.out.println("ENROLLED IN: " + courses.toString());
		
	}

	// viewBalance() method
	public void viewBalance() {
		System.out.println("Your balance is: $" + tuitionBalance);
	}
	
	// payTuition() method
	public void payTuition() {
		viewBalance();
		
		System.out.println("Enter your payment amount: $");
//		Scanner scan = new Scanner(System.in);
		
		int payment = scan.nextInt();
		tuitionBalance -= payment; // this means: tuitionBalance = tuitionBlaance - payment
		
		System.out.println("Thank you for your payment of: $" + payment);
		viewBalance();
		
	}


	// 5. Show Info 
	public void showInfo() {
		System.out.println("\nName: " + this.firstName + " " + this.lastName +  
				"\nStudent ID: " + this.studentId + 
				"\nClasses enrolled in: " + this.courses.toString()+ 
				"\nMonthly Dues: " + this.tuitionBalance);
		
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
