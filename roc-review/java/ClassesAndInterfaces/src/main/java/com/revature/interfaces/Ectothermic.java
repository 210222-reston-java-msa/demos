package com.revature.interfaces;

// Ectothermic means that any class (or creature) that implements this, has the following capabilities
// to change the temperature of its body
public interface Ectothermic {
	
	// interfaces are IMPLICITLY abstract -- this means we don't need to use the abstract keyword.
	// also IMPLICITLY public 
	void coolDown();
	
	void heatUp();

}
