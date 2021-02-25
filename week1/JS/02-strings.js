"use strict"
// strict mode will not allow us to declare a variable 
// in JavaScript WIHTOUT a keyword (like var, let or const)
// it must be declared with var, const, or let
// strict mode allows more "secure" javascript

var a = 'hello' // by default this variable is declared with var
a = "goodbye"
console.log(a)

const unchangeableWord = "Never"; // kind of like the final keyword in java

let templateLiteral = ``;

let templateLiteral = ``;
// We can use the backtick character to declare what are
// called template literals. It is effectively a string, however
// it supports string interpolation.

// String Interpolation means that we can insert placeholders for variables
// and the template literal will use the value of the variable
// when used.

templateLiteral = `This is a multi-line template literal... This is 
one of the benefits of a template literal
`;

templateLiteral = `Five plus Five = ${5 + 5}`;
let age = 97;
// this is string concatenation (doesn't provide us as much flexibility as String Interpolation ${}) 
let mySentance = "Hello, I am " + age + " years old";

let food = 'Hamburger';
food = `Salad ${5 + 900}`;

templateLiteral = `I would like 1 ${food}`;

console.log(templateLiteral)
