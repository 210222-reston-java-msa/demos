// ctrl + k, s

// A hoisted variable means that it is treated as if it were declared at the top of the global scope

console.log(myVariable); // Output: undefined
var myVariable = "Look I'm defined";
// if we run the program from here, it won't throw us an error

// the above is equivelent to:
var myVariable; // here we DECLARE but we do NOT redefine or reset the value
console.log(myVariable)
myVariable= "Look I'm defined!"


// What about functions?

var myFunc = function() {

    console.log("Func variable is " +  funcVariable);
    console.log("ifVairable is " + ifVariable);


    var funcVariable = "In function!"

    if (true ) {
        var ifVariable = "If block!"
    }

};
myFunc();

// Hoisitng is a JS mechanism in which variables (and function declarations) are moved to the top
// of their scope before code execution. (only if they are declared iwth VAR.
