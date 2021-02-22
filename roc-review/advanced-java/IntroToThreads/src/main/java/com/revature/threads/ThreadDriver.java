package com.revature.threads;

public class ThreadDriver {

	public static void main(String[] args) {
		// This is where I'm going to run some custom made threads
		
		/*
		 * A process is the execution of an application/program. (Chrome)
		 * 
		 * A Thread is a "lightweight process" that is a segment or line of execution
		 * within the heavier process. (A tab playing a youtube video)
		 * 
		 * Threads compete for resources such as processing power, memory, etc.
		 * Threads share resources with each other.
		 * 
		 * Multithreading - multiple threads of execution within an operating system.
		 * Many threads running at once, simultaneous.
		 * Multithreading is an essential feature of the Java platform. 
		 *
		 */
		
		System.out.println("Program start.");

		Employee emp1 = new Employee(); // Thread state: NEW 
		
		emp1.setPriority(10);
		emp1.start(); // Thread state: RUNNING -- the start() method calls the run() method
		// we have overridden the run() method
		System.out.println(emp1.isAlive());
		
		MyRunnable myR = new MyRunnable(); // This is simply a Runnable object
//		myR.run();
		
		Thread aNewThread = new Thread(new MyRunnable());
		// The parameter of this thread class is whose run() method is invoked
		aNewThread.start();
		
		Employee emp2 = new Employee(); // I instanitated a 2nd thread. It's NEW
		
		emp2.setPriority(1);
		emp2.start();
		
		/*
		 * join() method
		 * 
		 * The join method will tell our thread to wait until the specified thread
		 * completes its execution.
		 */
//		try {
//			emp1.join(); // this will make this thread go into a waiting state 
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		} 
		
		
		
		System.out.println("Program end.");
		
		// Display the priority of threads:
		System.out.println(emp1.getPriority());
		System.out.println(emp2.getPriority());
		
		// Check to see if a given thread is alive or dead
		System.out.println(emp1.isAlive());
		System.out.println(emp2.isAlive());
		
	}

	
	
	
	
}
