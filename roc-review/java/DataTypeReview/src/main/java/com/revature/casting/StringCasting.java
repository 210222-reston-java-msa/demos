package com.revature.casting;

public class StringCasting {

	// Converting data types continued...
	public static void main(String[] args) {
	
		String data = "10";
		
		// convert the String variable to an int before processing it
		int newData = Integer.parseInt(data);
		
		System.out.println(addForty(newData)); // should return 50
		
		
		// Convert integer to String
		int num = 10;
		String str = String.valueOf(num);
		
		System.out.println(str + 50); // 1050
		
	}
	
	public static int addForty(int x) {
		
		return x + 40;
	}
	
	
	
	
}
