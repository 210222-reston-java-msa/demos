package com.revature.interfaces;

/*
 * Interfaces can extend one another
 */
public interface Amphibious extends Swimmable {
	
	// the swim() method is abstracted away from us
	// Note that Amphibious will INHERIT the abstract swim method.
	
	default String returnAWord() {
		return "This is a Word-ish!";
	}

}
