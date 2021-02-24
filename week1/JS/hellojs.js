/*
    2 categories of datatypes

    1. PRIMITIVE datatypes -- refers to a single value (no behavior, just 1 state)
    2. REFERENCE datatype -- refers to a GROUP of states/and or behaviors (think about an object!)

    PRIMITIVE DATATYPES:
    - boolean
    - number
    - string
    - null
    - undefined 

    REFERENCE DATATYPES:
    - functions
    - objects
        - arrays
*/

// let and const ... are other ways to declare variables 
// variables declared with car are automatically global
var num = 5 // number
num = "hola"; // we just reassigned it to a string without any problem

// No need for a semi-colon, but it is reccomended.

var isApproved = true; // or could be false
var noMansLand = undefined;

h = "hello"; // by default if we don't declare with VAR, it's var.. but not best practice.

var word = "myWord";

let b = "something"; // let allows us to modify this variable, it is also restricted to a particular scope.

console.log(b);

b = "somethingElse";
