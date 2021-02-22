package com.revature.arrays;

import java.util.Arrays;


public class Driver {

	public static void main(String[] args) {
		
		// an int or a String can store single values.
		int x = 10;
		String name = "Sophia"; // a string is an object...it is an array of characters
		String sentence = "This is a sentence";
		
		System.out.println(x + name + sentence);
		
		/*
		 * we use ARRAYS to store multiple values
		 * an ARRAY can only store one type of data
		 * an ARRAY is a collection of elements
		 * 
		 * [] always denote arrays
		 */

		int[] numbers = new int[10];
		// 2 steps to creating an array:
		// 1. declare the array (and it's data type)
		// 2. we INITIALIZE the array with a certain capacity
		
		// _0_1_2_3_4_5_6_7_8_9 --> 10 spaces indexed 0-9
		// 0 is the default value for an int
		
		numbers[0] = 10;
		numbers[1] = 20;
		
		System.out.println(numbers);// this returns hash address in heap
		System.out.println(Arrays.toString(numbers));
		
		// Challenge: create a String array with 5 spaces (to fit 5 different strings)
		// call this String array words
		
		// Answer:
		String[] words = new String[5];
		words[0] = "mouse";
		words[1] = "dog";
		words[2] = "cat";
		words[3] = "rat";
		words[4] = "lion"; 
		
		System.out.println(words);
		System.out.println(Arrays.toString(words));
		
		// later we will discuss loops and how to iterate over arrays
		
		String[] names = {"Mary", "Larry", "Harry" };
		
		String girl = names[0]; // we are using a refernce variable to refer to an element AT a particular
		// position within the array.
		System.out.println(girl);
		
		int[] ages = {42, 13, 3, 99};
		// when I use curly braces with an Array, I am immediately assigning values AND capacity to my array
	
		
		
	}

}
