package com.revature;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.repository.FoodRepo;

public class Main {
	
	public static ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	
//	public static FoodRepo foodRepo = appContext.getBean("foodRepo", FoodRepo.class);

	public static void main(String[] args) {
		
		// We still need to load our Application Context

		

	}
	
	public static void insertInitialValues() {
		// insert food into the db
	}

}
