package com.revature.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.Associate;

public class StreamDriver {

	private static Logger log = Logger.getLogger(StreamDriver.class);

	/*
	 * In Java 8, the Stream API was introduced. This is used to process a
	 * collection of objects. What is a stream?
	 * 
	 * A stream is a SEQUENCE of objects. A stream is a wrapper around a data source
	 * which allows us to apply special methods which make bulk processing
	 * convenient and fast.
	 * 
	 */
	public static void main(String[] args) {

		// 1. instantiate an ArrayList for Associates
		List<Associate> associateList = new ArrayList<Associate>();
		associateList.add(new Associate(1, "Bobby", "Smith"));
		associateList.add(new Associate(2, "Tony", "Stark"));
		associateList.add(new Associate(3, "Bruce", "Banner"));
		associateList.add(new Associate(4, "Clint", "Barton"));

		// Call in our iterate
		// streamIterate(associateList);

		// filter here
		streamFilter(associateList, "t");
		// in the indexOf() method if a letter does not exist, it returns -1 as it's
		// index

		int[] arr = { 1, 400, 6, 7, 8, 9, 10 };
		double avg = streamAvg(arr);
		int max = streamMax(arr);
		System.out.println("The average of the array is " + avg);
		System.out.println("The max of the array is " + max);
	}

	// Iterate
	public static void streamIterate(List<Associate> associateList) {
		// in forEach() we pass a Consumer<T> then specify the action in a lambda
		associateList.stream().forEach((Associate a) -> {
			System.out.println(a.getFirstname());
		});

	}

	public static void streamFilter(List<Associate> associateList, String filter) {
		// with filter() we pass a Predicate

		String filter2 = filter.toLowerCase();

		associateList.stream()
				.filter((Associate a) -> new StringBuilder(a.getFirstname().toLowerCase()).indexOf(filter2) != -1)

				.forEach((Associate a) -> {
					System.out.println(a.getFirstname());

				});
	}

	// Max
	public static int streamMax(int[] arr) throws IllegalArgumentException {

		if (arr == null) {
			log.warn("User sent null array");
			throw new IllegalArgumentException("Can't process null array");
		}

		return Arrays.stream(arr).max().getAsInt();

	}

	// Avg
	public static double streamAvg(int[] arr) throws IllegalArgumentException {

		if (arr == null) {
			log.warn("User sent null array");
			throw new IllegalArgumentException("Can't process null array");
		}

		return Arrays.stream(arr).average().getAsDouble();

	}

}
