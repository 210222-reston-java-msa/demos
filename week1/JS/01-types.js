var num = 5;

// A JS function
function showType(input) {
    console.log('input is ' + input + ' and is of type ' + typeof(input));
}

// invoke the function
showType('hello world!');
showType(9000);

var x = 10;
showType(x);

// in 2015 ECMA Script 6 (aka ES 6) was introduced and now we can declare variables with const and let
// it also introduced arrow functions
const turnRed = (animal) => {
    console.log(animal + " is red now")
};

turnRed("cat");

// All objects in javascript are surrounded by {};
var emptyObj = {};

var sampleObject = {
    key1: 1,
    key2: 'hello',
    key3: true,
    "anotherField": 30
};

var person = {
    firstName: 'bob',
    lastName: 'Smith'
};

console.log(sampleObject);
console.log(person.firstName);

// this is how we declare an Array in JavaScript
var list = [1, 2, 3, 4, 500, 600, 13];

const a = "ten" // string -- when we declare a variable with let we are able to modify it
const b = 10 // number
const c = true // boolean
const d = {} // empty object
const e = null; // null
const f = [];
const g = function(){};

var arr = [a, b, c, d, e, f, g];
console.log(arr);

// since I've declared all of these variables as const...
// I can NOT change them now.

a = "ten thousand";
console.log(a);
