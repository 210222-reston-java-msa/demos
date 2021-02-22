package com.revature.collections;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/*
 * Why Collections?
 * 
 * Previously we've learned about Arrays, which are fixed non-primitive collections of elements.
 * We will utilize the Collections framework to manipulate dynamic collections of objects.
 * 
 */
public class CollectionDriver {

	public static void main(String[] args) {
		
		// an example of an integer array
		int[] numbers = {1, 2, 3, 4, 5};
		String[] words = {"This", "is", "a", "String", "Array"};
		// an array is an object
		numbers[1] = 2003;
		System.out.println("The amount of Strings in my String array is " + words.length);
		
		// print out the number at index 3
		System.out.println(numbers[3]);
		
		
		// we could work with this Array like so:
		// we could create a for loop and print out each element
		for (int i=0; i<numbers.length; i++) {
			
			System.out.println(numbers[i]);
		}
		
		/*
		 * The Problem with arrays is that they are STATIC! ...meaning we can't change them
		 * We can only store one data type within them and we can't expand Arrays.
		 */
		
		/*
		 * List
		 * 
		 * A List is an ordered Collection (sometimes called a sequence). Lists may contain duplicate elements.
		 * In addition to the operations inherited from Collection, the List interface includes operations for the following:
		 * 
		 * 		+ Positional access 
		 * 			- manipulates elements based on their numerical position in the list. This includes 
		 * 			  methods such as get, set, add, addAll, and remove.
		 * 
		 * 		+ Search 
		 * 			- searches for a specified object in the list and returns its numerical position. 
		 * 			  Search methods include indexOf and lastIndexOf.
		 * 
		 * 		+ Iteration 
		 * 			- extends Iterator semantics to take advantage of the list's sequential nature. 
		 * 			  The listIterator methods provide this behavior.
		 * 
		 * 		+ Range-view 
		 * 			- The sublist method performs arbitrary range operations on the list.
		 * 
		 * The Java platform contains two general-purpose List implementations. LinkedList, which performs well when adding or removing from 
		 * the collection frequently. A ArrayList which offers better performance when the collection will be accessed frequently (get and 
		 * set methods).
		 */
		
		List<Integer> nums = new ArrayList<Integer>(); // Here I am INFERRING GENERICS
		// I am telling the Collection (which is ArrayList) what type of Object it can store.
		nums.add(4);
		nums.add(8);
		nums.add(7);
		
		System.out.println(nums);
		
		User u = new User("James", "Bond", "james007", "p4ssw0rd", "jbond@gmail.com");
		
		User u2 = new User("hulk24", "1r34", "bb@gmail");
		
//		System.out.println(u.getLastName());
		System.out.println(u);
		System.out.println(u2);
		
		// let's create an ArrayList of Users
		List<User> users = new ArrayList<User>();
//		System.out.println(users);
		users.add(u);
		users.add(u2);
		users.add(new User("Blake", "Kruppa", "bkruppa", "pass", "bk@gmail.com"));
		users.add(new User("Larry", "King", "lk123", "pwd", "lking@gmail.com"));
		users.add(u);
	
		// Enhanced for-loop (for-each loop) is the best  way to traverse a collection
		
		// for (<Type> *each-element* : (within) *The Collection*) {
		//				// the code to do to each-element
		// }
//		for (User e : users ) {
//			e.setFirstName("Tony");
//			e.setLastName("Balongna");
//		}
		
		System.out.println("============================");
		
		//   this is each element    users = the collection 
		for (User individualUser : users) {
			System.out.println(individualUser);
		}
		
		/*
		 * Set
		 * 
		 * A Set is a Collection that cannot contain duplicate elements. It models the mathematical set abstraction. The Set 
		 * interface contains only methods inherited from Collection and adds the restriction that duplicate elements are 
		 * prohibited. Set also adds a stronger contract on the behavior of the equals and hashCode operations, allowing Set 
		 * instances to be compared meaningfully even if their implementation types differ. Two Set instances are equal if they 
		 * contain the same elements.
		 */
		System.out.println("-----------------------------------------------------------------------");
		
		HashSet<User> userSet = new HashSet<User>();
		User z = new User("Zafar", "Kahrov", "zkjava", "12345", "zk@yahoo.com");
		User z2 = new User("Zafar", "Kahrov", "zkjava", "12345", "zk@yahoo.com");
		
		
	
		userSet.add(new User("Mary", "Shelley", "ms", "pass", "ms@gmail.com"));
		userSet.add(new User("Jack", "Sparrow", "captainjack", "parrot", "pirate@gmail.com"));
		userSet.add(new User("Bruce", "Wayne", "batman", "bats$$", "batman@gmail.com"));
		userSet.add(new User("Jack", "Sparrow", "captainjack", "parrot", "pirate@gmail.com"));
		userSet.add(new User("Zafar", "Kahrov", "zkjava", "12345", "zk@yahoo.com")); // the compiler thinks
		// this is a totally different object....
		userSet.add(z);  // the compiler knows that we can't fool it!
		userSet.add(z); // the compiler knows that z points to the same object
		userSet.add(z);
		// this compiles just fine....
		
		// enhanced for-loop to print out each user
		for (User user : userSet) {
			System.out.println(user);
		}
		
		System.out.println("----------------------------------");
		
		// == compares the address in memory (are these objects the SAME thing?)
		// .equals() asks are these objects equal in properties?
		System.out.println(z);
		System.out.println(z.equals(z2));
		
		
		/*
		 * Queue
		 * 
		 * A queue is a collection designed for holding elements prior to processing.  Besides basic Collections operations 
		 * (adding, removing elements), the queue provides us different insertion, extration and inspection operations.
		 * 
		 * A Queue maintians an order called FIFO
		 * This stands for first in, first out. 
		 * 
		 * 
		 */
		
		System.out.println("=========================================================");
		// shift + ctrl + o
		// covariance!
		Queue<User> userQueue = new LinkedList<User>();
		userQueue.add(new User("Mary", "Shelley", "ms", "pass", "ms@gmail.com"));
		userQueue.add(new User("Jack", "Sparrow", "captainjack", "parrot", "pirate@gmail.com"));
		userQueue.add(new User("Bruce", "Wayne", "batman", "bats$$", "batman@gmail.com"));

//		while( ) { // the parenthesis mark a condition that must be true for while to run
//			// perform some code
//		}
		
		while(userQueue.size() != 0) {
			System.out.println("Queue size: " + userQueue.size());
			System.out.println("Processing: " + userQueue.poll()); // poll() method is only available for Queues
		}
		
	}

}
