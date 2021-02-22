package com.revature.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.LocalDateTime;

/*
 * Reflection (as a concept) is a language's ability to inspect and dynamically call/modify classes, methods, 
 * interfaces and properties at runtime.
 * 
 * Reflection API is new in Java 8. Reflection gives us info about the class to which an Object belongs.
 * 
 * Through Reflection we can invoke methods irrespective of their access modifiers.
 */

public class ReflectionExample {

	// fields
	private String name;
	private int id;
	private LocalDateTime todaysTime;

	public ReflectionExample() {

	}

	public ReflectionExample(LocalDateTime todaysTime) {
		super();
		this.todaysTime = todaysTime;
	}

	public ReflectionExample(String name, int id) {
		super();
		this.name = name;
		this.id = id;
	}

	public ReflectionExample(String name, int id, LocalDateTime todaysTime) {
		super();
		this.name = name;
		this.id = id;
		this.todaysTime = todaysTime;
	}

	public static void main(String[] args) {

		/*
		 * Every Java class has a single instance of class Runtime which allows the app
		 * to interface with the environment in which the app is running.
		 * 
		 * The current runtime can be obtained from the getRuntime() method.
		 */
		Runtime run = Runtime.getRuntime();
		ReflectionExample re = new ReflectionExample(); // instantiate an object of the above class type

		System.out.println("Class Name: " + re.getClass().getName());
		System.out.println("Simple Class Name: " + re.getClass().getSimpleName());
		
		// RUNTIME FACTS OBTAINED FROM THE RUNTIME OBJECT
		System.out.println("Free memory: " + run.freeMemory()); // returns the amount of memory in the JVM in BTYES
		System.out.println("Max memory: " + run.maxMemory()); // returns the max amount of memory in the JVM in BTYES
		System.out.println("Total memory: " + run.totalMemory());

		// to print out all of the constructors for a class, we would need to leverage
		// the introspective ability of Reflection
		for (Constructor<?> c : re.getClass().getConstructors()) {

			System.out.println(c);
		}
		
		// ASHA256 Hashing ALGO == LOOK UP MAVEN DEPENDIENCIES
		// ORM -- object realtional mapper -- Hibernate Spring Data
		
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		// we import Field class from java.lang.reflect
		Field[] fields = re.getClass().getDeclaredFields();
		
		for (Field f : fields) {
			System.out.println(f);
		}

		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
		
		// the method Field.getModifiers() can be used to return an integer representing the set 
		// of declared modifiers for a field....
		
		int reflectionMods = re.getClass().getModifiers();
		System.out.println("Public? : " + Modifier.isPublic(reflectionMods));
		System.out.println("Private? : " + Modifier.isPrivate(reflectionMods));
		
	}

}
