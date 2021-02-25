// Object Oriented Programming in JavaScript

/*
    OOP Concepts that we will cover :
    1. Defining a class
    2. Instantiating a class
    3. Inheriting from a class
*/

// First  we have to consider that EVERYTHING is treated as an object
// I mean: strings, functions, numbers...when we covered closures, we saw
// that we can encapsulate data/states/properties/fields within a function (so the function
// itself is kind of like a class...)

function Computer(compName, ramSizeGb, cpuCores, cpuSpeedGhz) {

    this.compName = compName;
    this.ramSizeGb = ramSizeGb;
    this.cpuCores = cpuCores;
    this.cpuSpeedGhz = cpuSpeedGhz;

    this.printSpecs = function() {
        console.log(`This computer is named ${compName}.  It has ${ramSizeGb} GB RAM, and
        a ${cpuCores}-core ${cpuSpeedGhz} GHz processor.`)
    }

    // notice there is no return statment!
};

// new creates an empty object and assigns it the properties AND values
var myComputer = new Computer("MyPC", 4, 4, 4.2);
myComputer.printSpecs();

// Things to Note:
/*
    1. The function Computer is a constructor function that doubles 
    as a class definition (practically speaking)

    2. The new keyword creates a new, empty object, then fills out the properties
    according to the Computer function.  The new keyword is what calls the Constructor
    in context to that particular object that is created, and sets those properties that we defined.
*/

// this is not typical
function ComputerReWrite(compName, ramSizeGb, cpuCores, cpuSpeedGhz) {

    // this is what the new keyword does
    var obj = {};

    obj.compName = compName;
    obj.ramSizeGb = ramSizeGb;
    obj.cpuCores = cpuCores;
    obj.cpuSpeedGhz = cpuSpeedGhz;

    obj.printSpecs = function() {
        console.log(`This computer is named ${compName}.  It has ${ramSizeGb} GB RAM, and
        a ${cpuCores}-core ${cpuSpeedGhz} GHz processor.`)
    }

    return obj;
}

// And use the rewrite like so:
// here i don't use the new keyword becuase I create an empty obj and then give it properties and THEN return it
var myComputerRewrite = ComputerReWrite("MyBetterPC", 8, 8, 4.2);
myComputerRewrite.printSpecs();
console.log(myComputer);

// Putting a function IN a constructor is NOT a good idea, because we can't actually see it when we print the object

// This is what the prototype property is for...

// We use prototype whenever we wants every instance of an objects 
// to have the same functionaloity. We attach the function to the prototype
function ComputerProto(compName, ramSizeGb, cpuCores, cpuSpeedGhz) {
    // this is proper constructor
    this.compName = compName;
    this.ramSizeGb = ramSizeGb;
    this.cpuCores = cpuCores;
    this.cpuSpeedGhz = cpuSpeedGhz;

    // notice, no functions! we're doing good....
}


// every ComputerProto object must have a print specs function
ComputerProto.prototype.printSpecs = function() {
    console.log(`This computer is named ${this.compName}.  It has ${this.ramSizeGb} GB RAM, and
    a ${this.cpuCores}-core ${this.cpuSpeedGhz} GHz processor.`)
}

console.log('---------------------------------------------------')
var myComputerProto = new ComputerProto("MyPC", 4, 4, 4.2);
myComputerProto.printSpecs();

// Now that we have an object with a configured prototype
// we can  even inherit from it.  In order to inherit, we use the call() function.
// call() allows us to define "this" in the context of the function.

// Let's define a Laptop Child class
function Laptop(compName, ramSizeGb, cpuCores, cpuSpeedGhz, weight) {

    ComputerProto.call(this, compName, ramSizeGb, cpuCores, cpuSpeedGhz);

    // think of the above as a super() keyword
    this.weight = weight;
}

var myLaptop = new Laptop("MyLaptop", 4, 4, 3.0, 5);
// myLaptop.printSpecs(); // this won't work!

/*
    We were able to inherit the properties we defined for ComputerProto, but not the functions..
    That's because we defined the functions on the PROTOTYPE of ComputerProto

    In order inherit functions properly, we need to do 2 things:

    1. Set the prototype of the child = to that of the parent by using Object.create()

    2. We have to se our constructor to equla that of our child, rather than the parent.
*/

// This is called "parasitic combination inheritance"
var computerCopy = Object.create(ComputerProto.prototype); // copy ComputerProto's prototype into a new obj
computerCopy.constructor = Laptop; // set this prototype's constructor = to Laptop's
Laptop.prototype = computerCopy; // set Laptop's prototype == to computerCopy (has constructor + functionality from prototype)

// look into Mozilla's method for parasitic combo inheritance.
var myLaptop2 = new Laptop('MyLaptop2', 4, 4, 3.0, 5);

// we need to overwrite the printSpecs() for laptop....
Laptop.prototype.printSpecs = function() {
    console.log(`This computer is named ${this.compName}.  It has ${this.ramSizeGb} GB RAM, and
    a ${this.cpuCores}-core ${this.cpuSpeedGhz} GHz processor.  OH and the weight is ${this.weight}`)
}


//we overWROTE not overrode....

myLaptop2.printSpecs();







