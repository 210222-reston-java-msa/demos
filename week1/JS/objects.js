let personLiteral = {
    firstName: 'Wilson',
    hairColor: 'hot pink',
    favoriteFood: 'bbq'
}

/*
    JS allows use to create Object Literals which is nothing more than an object with favFood-value pairs surrounded in curly brackets.
*/

console.log(personLiteral); // JSON - JavaScript Object Notation
console.log(personLiteral.favoriteFood);
console.log(personLiteral["favoriteFood"]); // same value as above, just accessing the value of the favFood as a string;

let food = "favoriteFood";
let hc = "hariColor"
console.log(personLiteral[food]);
// bracket notation is useful when we might not remember the name of the key when we access the code]

// JS allows us to add properties to an obj literal
personLiteral.age = 35;

// ============================== There is another way to create a Person object =======================================

function PersonOldWay(firstname, hairColor, favFood) {

    this.firstName = firstname;
    this.hairColor = hairColor;
    this.favFood = favFood;

    // this is not best practice to define common functionalty for an obj
    this.sayHello = function () {
        console.log(`Hello, my name is ${firstName}`)
    }
}
// Eventually in ES6 (2015) Classes were added
class Person{

    constructor(firstName, hairColor, favFood) {

        this.firstName = firstName;
        this.hairColor = hairColor;
        this.favFood = favFood;
    }

    // this is a method (function specific to an object)
    sayHello() {
        console.log(`Hello, my name is ${this.firstName}`)
    }


}

// a method is a function that is specific to an object (both are sets of instructions to complete a task)
var harry = new Person('Harry', 'brown', 'green beans');
console.log(harry);
harry.sayHello();