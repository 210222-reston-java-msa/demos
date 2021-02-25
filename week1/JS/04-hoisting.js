// Hoisting example

// In JavaScript, variables declared with var are hoisted.
//  This means they are treated as if they were declared at either
//  the top of the global scope or the function scope, if in a function,
//  even if they were defined somehwere in the middle. This can be wierd
//  and uncomfortable for procedural programmers.

// Let's look at some examples.

console.log(myVariable); // Ouput: undefined
var myVariable = "Look, I'm defined!";
// the above is equivalent to 
var myVariable;
console.log(myVariable); // Ouput: undefined
myVariable = "Look, I'm defined!";

// So, it wasn't DEFINED... but it *was* DECLARED. That is, we don't
//  have any errors complaining that the runner has no idea what a
//  'myVariable' is.

// What about functions?
// var globalVariable
console.log("Global variable: " + globalVariable); // Output: undefined
// console.log(funcVariable); // Output: Runner throws an error and execution immediately stops
var myFunc = function() {
    // var funcVariable
    // var ifVariable

    console.log("Func variable: " + funcVariable); // Output: undefined
    console.log("If variable: " + ifVariable);
    var funcVariable = "In function!";

    if (true) {
        var ifVariable = "If block!";
    }
} ();

var globalVariable = "Global variable!";

// In other words, hoisting still happens at the function scope, but not past it.
//  In addition to that, variables declared with var inside a for block, for instance,
//  or an if block, are hoisted to the top of the function.

// Hope this helps! Useful links:
// Freecodecamp tutorial: https://www.freecodecamp.org/news/what-is-variable-hoisting-differentiating-between-var-let-and-const-in-es6-f1a70bb43d/