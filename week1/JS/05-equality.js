/*
JavaScript has 2 equality operators which are == vs. === (they return a boolean value)

Both compare the object on either side.
== performs something called "Type Coersion" ... in other words "10" == 10 is true

=== is a lot stricter.  "10" === 10 is false, because "10" is a string, and 10 is a number;
*/

function compare(first, second) {

    console.log(`
    first = ${first}, and is of type ${typeof(first)}
    second = ${second} and is of type ${typeof(second)}

    first == second evaultes to ${first == second}
    first === second evaluates to ${first === second}
    `)
}

compare("10", 10); // comparing string 10 to number 10
compare("10", "ten");
compare([], null);
compare({}, {}); // In javaScript 2 different object instances are NEVER equal to eachother;

var z = {
    a: 1,
    b: 1
}
var u = {
    d:"1",
    e:1
}
compare(z.a , u.d); // true with ++, false with ===

var product = 4 * 'hi'; // we can't sdo this because hi is a string, not a number
// console.log(product); // will reutrn NaN

console.log(parseInt("blablablabla"));

if (isNaN(product)) {
    console.log("You can't multiply a string!!!!")
} else {
    console.log(product)
}
var s = 5 / 'hi'; // NaN 
console.log('-----------------------------------')
console.log(Number.isNaN(s)) // true
var word = "blablabla"
compare(s, NaN);
//compare(s, NaN);  // NaN is a property of the global object

compare(Infinity, Infinity);

console.log(5/0);


