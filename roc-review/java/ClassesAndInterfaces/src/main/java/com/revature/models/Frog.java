package com.revature.models;

import com.revature.interfaces.Amphibious;
import com.revature.interfaces.Ectothermic;

// In Java you can only extend ONE class, but you can IMPLEMENT many INTERFACES
public class Frog extends Animal implements Ectothermic, Amphibious {
	
	
	@Override
	public void makeSound() {
		System.out.println("ribbit ribbit");
		
	}
	
	// overloading is the act of taking in a different type or number of parameters
	public void makeSound(int number) {
		
		for (int i=0; i<number; i++) {
			
			System.out.println("ribbit ribbit");
		}
		
	}

	public void coolDown() {
		System.out.println("too hot out, cooling down now");
		
	}

	public void heatUp() {
		System.out.println("too cold out, heating up now");
		
	}

	public void swim() {
		// TODO Auto-generated method stub
		
	}
	
	
}
