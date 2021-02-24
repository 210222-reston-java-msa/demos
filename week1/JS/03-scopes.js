/*
    2 Main scopes in JS
    1. Global Scope -- variables declared here are accessible from ANYWHERE
        - variables declared outisde of the function scope with VAR will be declared on a global scope

    2. Functional Scope -- variables declared within a function's block {}

    3. Lexical/Block scoping -- introduces in 2015 by ES6\
*/


function funScope() {
    var x = 'hello'; // here x is declared on a FUNCTIONAAL scope

    if (true) {
        var y = 'goodbye';
    }

    console.log(y)
}

console.log(x);
//funScope();