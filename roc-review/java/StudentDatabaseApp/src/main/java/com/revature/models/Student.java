package com.revature.models;

import java.util.Scanner;

import com.revature.Driver;

// ctrl + shift + s to save all
// ctrl + s to save 1
public class Student {
	
	// Student instance variables (properties or fields)
	private String firstName;
	private String lastName;
	private int gradeYear; // 1 = freshman, 2 = sophomore, etc...
	private String studentId;
	private String courses = "";
	private int tuitionBalance = 0;
	
	private static int costOfCourse = 600;
	// later, let's create a static ID of 1000 which increments everytime we add a new student
	
	private static int id = 1000; // everytime a student object is instantiated (created)
	// we increment this id by 1
	
	
	// Constructor: prompt the user to enter a student's name and year
	public Student() {
		Scanner scan = new Scanner(System.in);
		
		// capture the first name
		System.out.println("Enter the student's first name");
		this.firstName = scan.nextLine();
		
		// capture the second name
		System.out.println("Enter the student's last name");
		this.lastName = scan.nextLine();
		
		// capture the gradeyear -- here we're using escape sequences
		System.out.println("Enter Student grade year:\n1 - Freshman\n2 - Sophomore\n3 - Junior\n4 - Senior");
		this.gradeYear = scan.nextInt();
		
		// when a student is instantiated, they get their own unique id AND it increments the static id of the class
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
		// Get inside a loop, then the user will hit 'Q' for quit to quit the program

		do {
			System.out.println("Enter a course to enroll. (Q to quit)");
			Scanner scan = new Scanner(System.in);

			String course = scan.nextLine();

			if (!course.equalsIgnoreCase("Q")) {

				courses += "\n" + course;
				tuitionBalance += costOfCourse;

			} else {

				System.out.println("BREAK!");
				break;
			}

		} while(true);
		
		System.out.println("ENROLLED IN: " + courses);
		
	}

	// viewBalance() method
	public void viewBalance() {
		System.out.println("Your balance is: $" + tuitionBalance);
	}
	
	// payTuition() method
	public void payTuition() {
		viewBalance();
		
		System.out.println("Enter your payment amount: $");
		Scanner scan = new Scanner(System.in);
		
		int payment = scan.nextInt();
		tuitionBalance -= payment; // this means: tuitionBalance = tuitionBlaance - payment
		
		System.out.println("Thank you for your payment of: $" + payment);
		viewBalance();
	}

	@Override
	public String toString() {
		return "Student [firstName=" + firstName + ", lastName=" + lastName + ", gradeYear=" + gradeYear
				+ ", studentId=" + studentId + ", courses=" + courses + ", tuitionBalance=" + tuitionBalance + "]";
	}
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
