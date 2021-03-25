package com.example.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// This is a Spring Bean....We will confiugure our Spring beans in an Application Context
// A spring bean is a Java POJO (Plain Old Java Object) that is managed  by the Spring IoC Container (IoC === Inversion of Control)
public class MotdServiceBean {
	
	// Message of the day's
	protected List<String> motds = new ArrayList<String>();
	
	protected int defaultMotdIndex;
	
	// A method that returns the  message of the day (MOTD)
	public String getMotd(int motdIndex) {
		
		// this is validation logic, making sure that there is actually an MOTD with the index passed thru
		if (motdIndex < 0 || motdIndex >= motds.size()) {
			return motds.get(defaultMotdIndex);
		}
		
		return motds.get(motdIndex);
	}
	
	
	// My goal is that whenever this MOTDServicebean is instantiated, I want to run this method
	// I will do this with a custom init-method lifecycle hook which is invoked in a bean declaration
	protected void initMotds() {
		motds.addAll(Arrays.asList("Good Morning", "You always pass failure on your way to success",
				"It seems impossible until it's done", "Positive anything is better than negative nothing", 
				"The first step of the journey is always the most difficult"));
	}
	
	
	// Let's create a method which will be configured to inject a value from the app INTO the
	// class property using something called Setter Injection
	public void setDefaultMotdIndex(int defaultMotdIndex) {
		this.defaultMotdIndex = defaultMotdIndex;
	}
	

}
