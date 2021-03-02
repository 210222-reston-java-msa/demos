/*
    2 Main scopes in JS
    1. Global Scope -- variables declared here are accessible from ANYWHERE
        - variables declared outisde of the function scope with VAR will be declared on a global scope

    2. Functional Scope -- variables declared within a function's block {}

    **3. Lexical/Block scoping -- introduces in 2015 by ES6
*/

function funScope() {
    var e = 'hello'; // herx is declared on a FUNCTIONAL scope

    if (true) {
        var y = 'goodbye';
    }

    console.log(y)

}

funScope();

var scope = "I am global"; // global variable

function whatIsMyScope() {

    var scope = "I am local (or functional)"
    function func() {
        console.log(scope)
    }

    return func();
}

whatIsMyScope();

/*
  Lexical/block scoping was added in ES6
  It allows for block scoping similar to Java

  This is accomplished with new keywords, let and const
*/

function blockScope(){
    let c = "cat";

    var m = "mary"; // this variable declared inside of a function can NOT be accessed outside of the scope (even though it's var)

    if (true) {
        let d = "dog";
        //console.log(d) // the variable is only accessible within this scope
    }

    console.log(d); // we cannot access d "dog" because it is confined to the lexical/block
    console.log(c) // c is fine because it's in the same scope
}

blockScope();

console.log(m); // this won't work because m is declared within the functional scope.
