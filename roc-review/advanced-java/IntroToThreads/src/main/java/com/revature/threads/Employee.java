package com.revature.threads;

/*
 * There are 2 ways to create a cusom thread
 * 
 * 1. Extend Thread
 * 2. implement the Runnable interface.
 */
public class Employee extends Thread {

	/*
	 * Two important methods within a thread: 
	 * 
	 * 	- start() method -- we call this to
	 * create a new thread and THEN the run() method is executed 
	 * 
	 * 	- run() method
	 */
	
	@Override
	public void run() {
		
		// we will repeat that this instance's thread is working 10 times:
		for (int i=0; i<10; i++) {
			
			System.out.println(Thread.currentThread().getName() + " is working.");
		
			try {
				Thread.sleep(2000); //  This makes the execution pause for 2000 milliseconds
			} catch (InterruptedException e) {
				
				/*
				 * Every thread has an interrupt() method.
				 * IF this is called, then we break out of our for loop.
				 */
				
				e.printStackTrace();
			}
			
		}
		
		
		
	}

}















