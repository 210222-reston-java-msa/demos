// TypeScript is a Super Set of JavaScript
// ALL JS is valid TS...but not all TypeScript is valid JS

/*
    - TS is OOP -> it allows us more OOP features (interfaces, classes)
    - We get a TS compiler~! Which helps us check for errors
    - TS is Strongly Typed (we can enforce datatypes)

    TypeScript is transpiled into JavaScript.  Browsers can ONLY read JavaScript
    This means that all of our TS code must be transpiled into javascript at compilation time
    in order for our app to run on the web (in the browser)
*/

function logIt(message) {

    console.log(message);
}

var myMessage = 'hello world';

logIt(myMessage);