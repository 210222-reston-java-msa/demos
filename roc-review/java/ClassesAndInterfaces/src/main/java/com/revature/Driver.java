package com.revature;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.revature.models.Animal;
import com.revature.models.Cat;
import com.revature.models.Frog;

public class Driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
//		Cat c1 = new Cat();
//		Frog f1 = new Frog();
//		
		// Covariance: Is wehn we specifify that we are creating an object
		// that is a SUBTYPE of another
		Animal kermit = new Frog();
		

//		
//		Cat tom = new Cat("Domestic Shorthair", true);
//		tom.makeSound();

		
//		Tadpole lilfrog = new Tadpole();
//		
//		System.out.println(lilfrog.numberOfLives);
		
		Cat c = new Cat();
		System.out.println(c.numberOfLives);
		
		
	}

}
