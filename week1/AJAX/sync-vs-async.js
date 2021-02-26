/*
    JavaScript is by default synchronous.

    This means that it executes our code line by line, block by block, in order.
    EXCEPT when var or functions are hoisted
*/

console.log('1');
console.log('2');
console.log('3');

/*
    Asynchronous means that something will wait for a time to finish
    or a REQUEST to RESPOND while the rest of code continues to executes
*/

console.log('1')

// this takes in a callback function
setTimeout(function afterTwoSeconds() {
    console.log('2');
}, 2000);

console.log('3');

/*

our aplication isn't hangin around waiting for our function to finish!
instead it keeps executing the rest of the code until the timeout is finished

*/