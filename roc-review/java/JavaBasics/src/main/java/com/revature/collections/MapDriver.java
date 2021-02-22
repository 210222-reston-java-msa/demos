package com.revature.collections;

import java.util.HashMap;
import java.util.Map;

public class MapDriver {

	public static void main(String[] args) {
		// Maps DO NOT extend from the Collection interface or the iterable interface.
		// Map is its own interface from java.util.Map
		// There are several classes that we can instantiate.  These are different types of Maps
		
		/*
		 * Maps consist of key value pairs
		 * think of a dictionary (key = word, value = definition)
		 * Maps do NOT allow duplicate KEYS, but they do allow duplicate VALUES
		 * 
		 * example: In a dictionary "timid" (key), "introverted in nature" (value)
		 * 							"shy" (key),   "introverted in nature" (value)
		 */
		
		// let's build a hashmap that represents a dictionary
		HashMap<String, String> dictionary = new HashMap<String, String>();
		dictionary.put("outgoing", "extroverted in nature");
		dictionary.put("intelligent", "sharp witted");
		dictionary.put("timid", "introverted in nature");
		dictionary.put("shy", "introverted in nature");
		
		System.out.println(dictionary);
		System.out.println("=======================================================================");
		// HashMaps don't have a particular order
		// LinkedHashMaps DO maintain order (of insertion) 
		// TreeMaps DO maintain order
		
		// in order to iterate through a map, we use an enhance for loop
		for (String word : dictionary.keySet()) { // this just prints the keys
			System.out.println("The word is " + word);
		}
		
		System.out.println("========================================================================");
		
		for (String definition : dictionary.values()) {
			System.out.println("The definition is " + definition);
		}
		
		for (Map.Entry<String, String> entry : dictionary.entrySet()) {
			System.out.println(entry.getKey());
			System.out.println(entry.getValue() + "\n"); // this is an example of an escape sequence
	
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
