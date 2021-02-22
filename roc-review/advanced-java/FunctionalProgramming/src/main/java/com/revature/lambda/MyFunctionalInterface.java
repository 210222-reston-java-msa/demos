package com.revature.lambda;

@FunctionalInterface
public interface MyFunctionalInterface {

	/*
	 * A Functional Interface only has ONE functionality to exhibit.  It is abstract. 
	 */
	
	// The @FunctionalInterface Annotation will not allow me another method.
	// void somethingElse();
	void printSomething();
	
}
