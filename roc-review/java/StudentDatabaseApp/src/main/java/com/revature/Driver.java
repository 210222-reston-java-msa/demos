package com.revature;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.revature.exceptions.NotAGradeException;
import com.revature.models.Student;
import com.revature.util.UtilityMethods;

public class Driver {

	public static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		run();
	}

	public static void run() {

		// 1. Ask the user how many students we want to add to the DB
		boolean inputIsValid = false;
		int numOfStudents = 0;

		while (!inputIsValid) {
			// * !
			System.out.println("Enter number of new students to enroll:");

			try {

				// * !
				numOfStudents = scan.nextInt();
				inputIsValid = true;

			} catch (InputMismatchException e) {
				scan.nextLine();
				// Call the nextLine() method immediately after nextInt() and discard the
				// returned value and "refresh" the scanner.
				System.out.println("Please enter a valid number..");
			}

		}

		// * !
		Student[] arrOfStudents = new Student[numOfStudents];

		// 2. Create n number of students
		for (int i = 0; i < numOfStudents; i++) {

			System.out.println("Enter the student's first name");
			String firstName = scan.next();

			// capture the second name
			System.out.println("Enter the student's last name");
			String lastName = scan.next();

			// capture the grade year and build a loop
			boolean validInput = false;

			int gradeYear = 0;
			while (!validInput) {

				System.out.println("Enter Student grade year:\n1 - Freshman\n2 - Sophomore\n3 - Junior\n4 - Senior");

				try {
					gradeYear = scan.nextInt();
					UtilityMethods.validateGradeYear(gradeYear);

					validInput = true;

				} catch (NotAGradeException e) {
					System.out.println(e.getMessage());
				} catch (InputMismatchException im) {
					System.out.println("Just encountered a " + im + ". Please enter a valid integer.");
				} finally {
					scan.nextLine();
				}

			}

			// instantiate the Student and initialize all instance variables with the
			// collected data.
			arrOfStudents[i] = new Student(firstName, lastName, gradeYear);

			// enroll them!
			arrOfStudents[i].enroll();

			System.out.println("Would you like to pay the tuition? (Y/N)");

			String input = scan.next();

			if (input.equalsIgnoreCase("y")) {
				arrOfStudents[i].payTuition();
			}

			System.out.println("==========================================");

		}

	}
}
