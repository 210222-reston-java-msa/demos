package com.revature.controlflow;

// Here we'll introduce control flow 
/*
 * Flow control refers to the practice of managing the way that an application runs and executes
 * by using special statements These statement types include:
 * 
 * - Decision making statements
 * - Looping statements
 * - Branching Statements
 */
public class Driver {

	public static void main(String[] args) {
		
		/*
		 * Decision Making Statements
		 * 
		 * There are five types of decision making statements:
		 * 		1. if statements
		 * 		2. if/else statements
		 * 		3. if/else-if statements
		 * 		4. nested if statements
		 * 		5. switch statements
		 */
		
		/*
		 * if Statements
		 *  
		 * An if statement consists a condition, followed by statement or a set of statements.
		 *  
		 * The statements gets executed only when the given condition is true. If the condition 
		 * is false then the statements inside if statement body are completely ignored.
		 */
		int num = 72;
		
		if(num < 100) {
			System.out.println(num + ", The variable \"num\" is less than 100");
		}
		
		/*
		 * if/else Statements
		 * 
		 * Syntactically there the beginning of an if/else statement is identical to the if statement.
		 * However, an additional else block (no conditional here) is appended to the end.
		 * 
		 * The statements inside of the if block would execute if the condition is true, and the statements 
		 * inside the else block would execute if the condition is false.
		 */
		num = 101;
		
		if(num < 100) {
			System.out.println(num + ", The variable \"num\" is less than 100");
		} else {
			System.out.println(num + ", The variable \"num\" is more than 100");
		}
		
		/*
		 * if/else-if Statements
		 * 
		 * An if-else-if statement is used when we need to check multiple conditions. In this statement we 
		 * have only one “if” and one “else”, however we can have multiple “else if”. It is also known as 
		 * if-else-if ladder.
		 * 
		 * The most important point to note here is that in if-else-if statement, as soon as the condition 
		 * is met, the corresponding set of statements get executed, rest gets ignored. If none of the condition 
		 * is met then the statements inside “else” gets executed.
		 */
		num = 100;
		
		if(num < 100) {
			System.out.println(num + ", The variable \"num\" is less than 100");
		} else if (num > 100) {
			System.out.println(num + ", The variable \"num\" is more than 100");
		} else {
			System.out.println(num + ", The variable \"num\" is exactly 100");
		}
		
		/*
		 * Nested if Statements
		 * 
		 * When there is an if statement inside another if statement then it is called the nested if statement.
		 * 
		 * The code within the outer if statement will execute if its conditional is true. The code inside of the 
		 * nested if statement will only execute if both the conditions the outer condition (otherwise, how would 
		 * it get to the nested if statement?) and its own condition are true.
		 */
		num = 64;
		
		if(num < 100) {
			System.out.println("The variable \"num\" is less than 100");
			
			if(num % 2 == 0) {
				System.out.println("The variable \"num\" is even.");
			}
		}
		
		/*
		 * switch Statements
		 * 
		 * A switch statement uses the 'switch' keyword followed by a set of parenthesis used to pass a value into the
		 * switch block. This value will then be compared to nested code blocks called 'cases'. Each case has a value
		 * associated to it that is compared to the value passed into the switch statement to determine if that case's
		 * code block should be executed. There can be an optional default block that will be executed if case none of
		 * the case conditions are met.
		 * 
		 * Unlike if/else-if statements, the switch statement can have a number of possible execution paths. A switch 
		 * works with the byte, short, char, and int primitive data types. It also works with enumerated types (Enum), 
		 * the String class, and a few special classes that wrap certain primitive types: Character, Byte, Short, and 
		 * Integer. 
		 * 
		 * There are more nuances to switch statements (such as the concept of "fall-through") that will be explored 
		 * later in the section on Branching Statements.
		 */
		String str = "greeting";
		
		switch(str) {
		case "greeting":
			System.out.println("Hello there, fellow coder!");
			break; // without break statements, we get fall-through!
		case "goodbye":
			System.out.println("Goodbye for now!");
		case "GREETING":
			System.out.println("Casing is important, huh?");
		default:
			System.out.println("Nothing matched...");
		}
		
		/*
		 * Looping Statements
		 * 
		 * Loops are used to execute a set of statements repeatedly until a particular condition is satisfied. In Java,
		 * there are three types of basic* loops:
		 *  
		 * 		- for
		 * 		- while
		 * 		- do-while.
		 * 
		 * * - There is another looping statement known as a for-each loop, or enhanced for-loop. This topic is covered in the section
		 * 	   on variable arguments
		 */

		/*
		 * for Loop
		 * 
		 * A for loop consists of the 'for' keyword, followed by a set of parenthesis that contain the initialization, conditional, and
		 * iteration logic of the loop itself, and the code block that will be executed. You can break the flow of a for loop down into
		 * four steps:
		 * 
		 * 	Step 1: 
		 * 		In for loop, initialization happens first and only one time, which means that 
		 * 		the initialization part of for loop only executes once.
		 * 	
		 * 	Step 2:
		 * 		Condition in for loop is evaluated on each iteration, if the condition is true 
		 * 		then the statements inside for loop body gets executed. Once the condition returns 
		 * 		false, the statements in for loop does not execute and the control gets transferred 
		 * 		to the next statement in the program after for loop.
		 * 
		 * 	Step 3:
		 * 		After every execution of for loop’s body, the increment/decrement part of for loop 
		 * 		executes that updates the loop counter.
		 * 	
		 * 	Step 4: 
		 * 		The program jumps back to Step 2 and condition is re-evaluated.
		 */
		
		int[] numbers = {1, 2, 3, 4, 5};
		
		for (int i=0; i<numbers.length; i++) {
			
			System.out.println(numbers[i]);
		}
		
		for (int i=0; i<=100; i++) { // post incrementation is shorthand for i = i+1;
			System.out.println(i);
		}
		
		// make a for loop to print out all the even numbers between 0 & 100;
		for (int i=0; i<=100; i+=2) { // i+= 2 is the shorthand way of incrementing by 2
			
			System.out.println("The even number is " + i);
			
		}
		
		// iterate through a loop from 0 to 4, incrementing i each time
		for(int i = 0; i < 5; i++) {
			System.out.println("The value of 'i' is: " + i);
		}
		
		// iterate through a loop from 10 to 4, decrementing i each time
		for(int i = 10; i > 3; i--) {
			System.out.println("The value of 'i' is: " + i);
		}
		
		// There is no restriction of the way that you manipulate the initialized variables of the for loop during the iterative step.
		for(int i = 0; i < 100; i+=3) {
			if(i == 0) {
				System.out.println(i);
			} else {
				System.out.print(", " + i);
			}
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
