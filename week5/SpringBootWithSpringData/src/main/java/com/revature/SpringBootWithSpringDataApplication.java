package com.revature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = com.revature.controller.FoodController.class)
public class SpringBootWithSpringDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWithSpringDataApplication.class, args);
	}

}
