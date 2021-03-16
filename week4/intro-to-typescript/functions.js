var myLog = function (message) {
    console.log(message);
};
// Long fat-arrow notation
var myLog2 = function (message) {
    console.log(message);
};
// Short fat-arrow notation
var myLog3 = function (message) { return console.log(message); };


// This is transpiled into ES5...so NO arrows!