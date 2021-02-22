package com.revature.scanner;

import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		/*
		 * So sometimes we might want to take in user input from the console.
		 *(Or from other sources like an InputStream)
		 *There is a built in class in Java called the Scanner class which allows us
		 *to read use input from the console.
		 *
		 *There is a special variable called System.in which is is a type of InputStream
		 *and represents the console
		 */
		
		System.out.println(System.in);
		
		// so the InputStream represents a stream of inputs coming through
		
		// First we create a scanner object, which we instantate from the Scanner class
		Scanner scan = new Scanner(System.in);
		// shortcut to import is ctrl + shift + o
		
		// Prompt the user
		System.out.println("Hello! Please enter your name:");
		
		// We store the user's input in a variable by using the nextLine() method
		String name = scan.nextLine();
		System.out.println("Enter your age:");
		int age = scan.nextInt();
		
		// After the user hit's enter, print a message that says welcome (the name of the user)
		System.out.println("Welcome " + name + ". You are " + age + " years old.");
		
		
	}

}
