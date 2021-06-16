package com.revature.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParserTest {

	public static void main(String[] args) {
		
		String query = "username=tmax&password=secret";
		
		String[] sepByParams = query.split("&");
		
		System.out.println(Arrays.toString(sepByParams));
		
		List<String> values = new ArrayList<>();
		
		for(String pair : sepByParams) {
			
			values.add(pair.substring(pair.indexOf("=")+1));
			
		}
		
		System.out.println(values.toString());
	}
	
	
}
