package com.revature.util;

import com.revature.exceptions.NotAGradeException;
import com.revature.models.Student;

public class UtilityMethods {

	/**
	 * A simple method to validate a grade year when registering a student.
	 * 
	 * @param gradeYear
	 * @throws NotAGradeException
	 * 
	 * https://stackabuse.com/how-to-make-custom-exceptions-in-java/
	 */
	public static void validateGradeYear(int gradeYear) throws NotAGradeException {

		if (gradeYear <= 0 || gradeYear > 4) {
			throw new NotAGradeException("Please enter a valid grade number between 1 and 4");
		}

	}

	
	
	/**
	 * This method is just used to print all the information of students iwthin an Array
	 * 
	 * @param students array
	 */
	public static void printStudentInfo(Student[] students) {

		for (Student s : students) {
			s.showInfo();
		}

	}

}
