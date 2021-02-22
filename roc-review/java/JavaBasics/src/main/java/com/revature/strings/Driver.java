package com.revature.strings;

public class Driver {
	
	/*
	 * String Pool
	 * 
	 * The String Pool is the area within the Heap where strings are stored.
	 * 
	 * Remember that objects are stored in the Heap, but a special part of it called the String Pool
	 * is specifically where Strings created with a String literal are stored. The String Pool holds strings 
	 * that are instantiated with the String literal " ".
	 */
	
	public static void main(String[] args) {
		
		// this String is declared using the String literal and it is stored in the String pool
		String str1 = "hi"; 
		
		// declare another String using the string literal
		// both str1 and str2 now point String object in memory within the String pool
		String str2 = "hi";
		
		System.out.println(str1 == str2); // do these ref varaiables point to the SAME object? the SAME address?
		// true
		
		// when we declare a String object using the new keyword, this is stored in the heap NOT the string pool
		String str3 = new String("hi");
		
		System.out.println(str1 == str3); // this will print as false because they have different addresses in memory
		
		System.out.println(str1.equals(str3)); // this will print as true because they have the same value;
		
		// STRINGS ARE IMMUTABLE OBJECTS
		// IF we want to mutate a String, we use a different type of class called StringBuilder or StringBuffer (this is fo
		// week3 when we cover threads.
		
		String s = "hello";
		System.out.println(s.substring(3)); // when we call methods from the String API, we don't mutate the string
		// wejust return a different value
		
	
		s = s.substring(3); // this value is a NEW string stored in the String Pool
		System.out.println(s);
		System.out.println(s.equals("hello")); // false
		
		String b = "goodbye"; // this is in the String Pool
		
		String b2 = new String("goodbye"); // This is entirely new object in the Heap (outside of String pool)
		
		System.out.println(b == b2);
		
		// the intern method will find or generate a String inside the String Pool that matches the current String
		System.out.println(b == b2.intern());
		
		// You can't simply call a reverse method on a String because it is IMMUTABLE!
		// If you want a reversed version of that String....you have to create an entierly new String object
		
		// StringBuilder and StringBuffer are different classes from String
		/*
		 * Both are similar to Strings except they can be modified.
		 * 
		 * StringBuffer is thread-safe and StringBuilder is not. (we will cover threads in week 3)
		 * Due to this extra safety, StringBuffer is slower.
		 */
		
		String test = "test";
		StringBuilder t = new StringBuilder(test);
		System.out.println(t.reverse());
		
		StringBuilder sb = new StringBuilder("Hello");
		// StringBuilder and StringBuffer ARE mutable!
		sb.reverse();
		
		System.out.println(sb);
		
		
		System.out.println(sb.append('y'));
		System.out.println(sb);
		
		// sb is now equal to "olleH"
		
		String word = new String("hello");
		
		System.out.println(word.substring(3)); // "lo"
		System.out.println(word);// lo? or is it still "hello"? still HELLO because Strings are immutable
		
		System.out.println(reversed("Testing"));
		
	}
	
	public static String reversed(String s) {
		
		String answer = "";
		
		for (int i=s.length()-1; i>=0; i--) {
			
			answer += s.charAt(i);
		}
		
		return answer;
	}

}
