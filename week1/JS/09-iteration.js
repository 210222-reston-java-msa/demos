
let a = "ten";
let b = 10;
let c = true; // boolean
let d = {}; // object
let e = null; // null
let f = ""; // fasley string
let g = 0; // falsey number
let h = []; // empty array  = object
let i = function(){}; // function is an object

var list = [a, b, c, d, e, f, g, h, i];

// standard for loop
for (let j =0; j< list.length; j++) {
    console.log(list[j]);
}
console.log('---------------------------------------------------------------------')
// enhance for loop
for(let element in list) {
    // Enahnced For loops in JS iterate through the indices rather than the values
    console.log("index is " + element);
    console.log(list[element]); // this will call the value
    console.log('-------------------------')
}
console.log('---------------------------------------------------------------------')
// For Each Function (takes in a callback function which is what we perform on each element within the array)
list.forEach(

    function(value) {
        console.log(value);
        console.log('---------------------')
    }
);

console.log('-----------------------------------------')

// For Each with => arrow notation ( a lot simpler)
list.forEach(
    (item) => {
        console.log(item)
        console.log('--------------------')
    }
);

// Later we'll talk about more functions (built in) which we can
// use on arrays.  map(), filter(), reduce();
