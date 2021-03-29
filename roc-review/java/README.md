# Week 1: Core Java
Be prepared to answer any of the following questions for QC and interviews on **Monday Jan. 25th, 2021**.  You may follow this guide, aptly titled [*How To Do Well In QC*](https://github.com/210119-ROC/demos/blob/main/week1/how-to-do-well-in-qc.md) to prepare you for QC.

## Basics
1. What is Java? / Explain some features of Java
2. What is JRE / JDK / JVM?
3. What is the difference between an object and a class?
4. What is the root class from which every class extends?
5. What are the primitive data types in Java?
6. What are the different variable scopes in Java?
7. What are the access modifiers in Java? Explain them.
8. What are the non-access modifiers in Java?
9. What is the difference between static and final variables?
10. What are the default values for all data types in Java?
11. What is a wrapper class? List them.
12. What is autoboxing / unboxing?
13. What are annotations?
14. How to pass multiple values with a single parameter into a method? (Var args) -- You will not be asked about this
15. What are static imports?
16. What methods are available in the Object class?
17. What is the difference between == and .equals()?
18. What is an enhanced for loop and what is a forEach loop?
19. What are the 3 usages of “super” keyword?
20. What is the first line of any constructor?
21. What happens if you don’t define a constructor for a class? Can you still instantiate it?

## OOP
1. What are the 4 pillars of OOP? Explain each and give examples of you implement them in Java code
2. What is the difference between an abstract class and an interface?
3. Can abstract methods have concrete methods? Can concrete (non-abstract) classes have abstract methods?
4. Can static methods access instance variables? Can non-static methods access static variables?
5. What are the implicit modifiers for interface variables? methods?
6. What is the difference between method overloading and overriding? What are the rules for changing the method signature of overloaded methods?
7. Can you overload / override a main method? static method? a private method? a default method? a protected method?
8. When do you use extends or implements keywords?
9. What are the implicit modifiers for interface variables / methods?

## Collections / Generics
1. What are collections in Java?
2. What are the interfaces in the Collections API?
3. What is the difference between a Set and a List?
4. What is the difference between an Array and an ArrayList?
8. Are Maps in the Collections API? What makes Map different from other interfaces?
9. List several ways to iterate over a Collection. How would you iterate over a Map?
12. What are generics? What is the diamond operator (<>)? 
<br>
<br>

<div align="center"><strong>Remember to harness the power of Google as you study.</strong></div>

[![Image from Gyazo](https://i.gyazo.com/bcde9cbc8b8b67bf3a4cefde4d069ba9.gif)](https://gyazo.com/bcde9cbc8b8b67bf3a4cefde4d069ba9)



## Java Study Guide

You should be able to explain and apply the following topics:

### Fundamentals
- Features, benefits, and drawbacks of the Java language
  - WORA (write once, run anywhere)
  - Backed by Oracle
  - Rich APIs (e.g. Collections API)
  - Object-oriented
  - Strongly/strictly typed
  - High level (e.g. memory handled via automatic garbage collection)
  - Verbose
- Stack vs Heap
- Purpose and contents of the JDK, JRE, and JVM
- Main method signature and significance
- Compiling and executing Java code on the command line
- Primitive data types
  - boolean
  - byte
  - char
  - short
  - int
  - float
  - double
  - long
- Operators
  - pre / post-increment
  - ternary
  - logical
- Scopes of variables
  - static / class
  - object / instance
  - method
  - block
- Control flow statements
  - for
  - enhanced for loop
  - if/else 
  - while
  - do-while
  - switch
- Class members
  - Fields
  - Methods
  - Constructors
  - Nested classes (advanced)
- Access modifiers
  - public
  - protected
  - default
  - private
- Non-access modifiers
  - static
  - final
  - abstract
  - synchronized (look this up!)
  - transient (look this up!)
- Packages and import statements
  - static imports
- this and super keywords
- JRE library classes
  - Object
    - equals, hashCode, and toString methods
  - String
  - Collections
  - Arrays
- Wrapper classes
  - Autoboxing / unboxing
- String pool
- == vs .equals()
- Making objects immutable (you can do this by making ALL fields private and not including any getters or setter methods.

### OOP
- Object-oriented programming principles
  - Abstraction
    - Abstract classes
    - Interfaces
  - Polymorphism
    - Overloading
    - Overriding
  - Inheritance
    - Inheriting from classes vs interfaces (i.e what's the difference between `extends`
 and `umplements`?  
 - Encapsulation
    - Using access modifiers with getters/setters
- Object vs class (A class is the blueprint for 
- Abstract classes
  - Difference between abstract and concrete class
- Interfaces
  - Implicit modifiers for variables and methods
  - When to use instead of abstract class

### Collections & Generics
- Collections API
  - Inheritance hierarchy
  - List, Set, Map, and Queue interfaces and their differences
  - Concrete implementations of above interfaces and their differences (Think `ArrayList`, `HashSet`, etc)
  - Using enhanced for loops

- Annotations
  - @Override
  - @Deprecated
- Generics
  - Generic classes
  - Generic methods
  - Diamond operator and type inference

