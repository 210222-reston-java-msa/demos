package com.revature.diningphilosopher;

import java.util.Date;

// we want to implement Runnable 
public class Philosopher implements Runnable {

	private Object leftFork;
	private Object rightFork;

	public Philosopher(Object leftFork, Object rightFork) {
		super();
		this.leftFork = leftFork;
		this.rightFork = rightFork;
	}
	
	
	private void doAction(String action) throws InterruptedException{
		
		System.out.println(Thread.currentThread().getName() + " " + action); 
		Thread.sleep((int) Math.random() * 1000);
		
	}
	
	
	
	private String currentTime() {
		
		return new Date(System.currentTimeMillis()).toString().substring(11, 19);
		
	}

	/*
	 * The run() method is where the work happens
	 */
	@Override
	public void run() {
		
		try {
			
			// 1. Open an infinite loop
			while(true) {
				
				// 2. invoke doAction() and have the indiviudal philosopher thread begin contemplating
				// the meaning of life
				doAction(currentTime() + ": Contemplating the meaning of life");
				
				/*
				 * We apply the synchronized keyword to a block of code.  Internally
				 * Java uses a monitor for the parameterized object to provide synchronization.  
				 * 
				 * These "monitors" bind to an object, thus all synchronized blocks of the same object
				 * can have only one thread executing them at the same time. 
				 */
				
				synchronized(rightFork){
					// 3. doAction() pick up the right fork
					doAction(currentTime() + ": Picked up right fork");
					
					// another synchronized block for the (leftFork)
					synchronized(leftFork) {
						// 4. do Action() pick up the left fork -- start eating
						doAction(currentTime() + ": Picked up left fork -- started eating.");
						
						// 5. put down left fork
						doAction(currentTime() + ": Put down left fork.");
					}

					// 6. put down right fork -- think some more.
					doAction(currentTime() + ": Put down right fork -- thinking some more.");
					return;
				}
			
			}
			
			
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			e.printStackTrace();
		}

	}

	
	
	
	
	
	
	
	
	
	
	
	
}
