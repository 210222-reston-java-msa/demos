// 4 main ways to declare functions in javascript

//1 . Function Declaration
function divide(a, b) {
    return a/b;
}

// 2. Function Expression
const divide2 = function (a, b) {
    return a/b;
}

// 3. Arrow function Expression
const divide3 = (a, b) => {
    return a/b;
}

// 4. Arrow funciton Expression - Concise
const divide4 = (a,b) => a / b;

var value = divide4(10, 5);
console.log(value);



// callback function is a function that we define to be called by another function

function startup() {
    console.log("We started the thing");
}

// window.onload = startup(); // <-- this is a callback function

// window.onload = () => { console.log("We started the thing");}

// objects consist of key value pairs
// the KEY of an object's property is a string value
// KEYS are Strings
var objLiteral = {
    firstName: 'bob',
    lastName: 'smith'
}

// There are several ways to access the same value
console.log(objLiteral["firstName"]); // output should be 'bob'
console.log(objLiteral.firstName);  // this is more typical than the ["key's string value"]

var sentance = `Properties of objLiteral are: ${objLiteral.firstName} as the Firstname and ${objLiteral.lastName}
as the lastname`;

console.log(sentance);

var strungOut = objLiteral.toString(); // By default, the toString() method come from Prototype
// in order to "customize" aka overwrite the toString() method for our custom object aka our object literal
// we must call the prototype

const o = new Object();
console.log(o.toString());

// we'll return to Objects, constructors, prototype, etc....




