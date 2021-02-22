package com.revature.threads;

/*
 * java applications are naturally threaded.  The runtime environment begins the execution of our program
 * with the main() method in one thread.
 * 
 * Garbage Collection for example, is a SEPARATE thread of execution.  garbage collection is an example of
 * multithreading that is invisible to the programmer.
 * 
 * If we create our own threads, we're giving priority to different tasks throughout our application.
 * And we can use CPU resources efficiently.
 * 
 */
public class MyRunnable implements Runnable {

	public void run() {
		// This is exactly the same method we had to override in the Employee class that
		// extended thread

		System.out.println("I'm a thread! (runnable!)");

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Thread is ending now...");

	}

}
