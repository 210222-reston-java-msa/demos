package com.revature;

import org.apache.log4j.Logger;
// these is similar to java.sql
// and other packages that we've imported from JDBC

import com.revature.models.Cactus;

public class LoggingDriver {

	// Our logger object BELONGS to LoggingDriver class
	private static final Logger log = Logger.getLogger(LoggingDriver.class);
	/*
	 * We use the .class property when there's not an instance of the class
	 * available
	 * 
	 * We are creating a logger object for this class by calling on a method, which
	 * is built in to framework itself.
	 */
	
	public static void main(String[] args) {
		
		/*
		 * Logging is the process of writing "log" messages
		 * during the execution of a program and sending these
		 * messages to a centralized location.
		 * 
		 * Logging allows you to report and persist error and
		 * warning messages, as well as info messages.  
		 * (This is called runtime statistics)
		 * 
		 * We will create a Logger with a method called getLogger();
		 */
		
		log.info("PROGRAM START!");
		
		
		Cactus c = new Cactus();
		log.info("Cactus created successful" + c.toString());
		
		
		
		try {
			int i = 10/0;
			
			log.info("Computation successful!");
			
		} catch (ArithmeticException e) {
			
			log.warn("WARNING: Division by zero not successful");
			
		}
		
		log.info("PROGRAM END!");
		
		/*
		 * There are different logging levels
		 * OFF: no logging at all
		 * ALL: Turns on all levels of logging
		 * TRACE: add more info to debug level logs
		 * DEBUG: This is mostly for devs and turns on the following levels:
		 * INFO: important business processed that have finished and "good news" is expected.
		 * -- System Admins typically use this.
		 * WARN: suggests that the app might be continued but you should take caution
		 * ERROR: this shouts something that somehting has gone very wrong
		 * FATAL: Very uncommon, but signals that the applicaiton will fail because
		 * something has gone catestrophically wrong 
		 */
		
		
		
		
	}
	
}
