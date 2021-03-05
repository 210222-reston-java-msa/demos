package com.revature.models;

public class DebugTest {

	static int MAX = 9000;
	
	public static void main(String[] args) {
		
		
		int myNum = 2;
		
		int m = multiply(myNum);
		
		System.out.println(10/0);
		
		Object o = new Object();

	}
	
	
	public static int multiply(int x) {
		
		return MAX * x;
		
	}
	
	

}
