/*
    What is closure? Closure is JS' attempt at encapsulation:
    It allows for 2 things:
    
    1. allows for an inner function to access and enclosing functions's variables
    2. allows for an inner function to preserve an enclosing function's scope chain when executing the function.


*/
var x = function cake() {
    // some variables declared here
    var flour = true;
    var cornMill = false;
    var cupsOfSugar = 1;

    function bake() {
        var heat = 350;
        console.log("Baking the cake at " + heat + " !");
        console.log(`flour: ${flour}`);
        console.log(`cornMill: ${cornMill}`);
        console.log(`Cups of Sugar: ${cupsOfSugar}`);
        
        heat++; // everytime 
        flour = !flour; // flips the boolean of SURROUNDING state
        cornMill = !cornMill;
        cupsOfSugar++;
        console.log('new heat is ' + heat)
        
        /*
            The bake() function has the following scope chains:
                - 1. Of course, it has access to its own scope chains
                - 2. It has access to the cake()'s scope
                - 3. It has access to the global(outermost) scope.
        */
    }

    // this function is never returned, and we will never see its output
    // function sayHi() {
    //     console.log("hi!")
    // }

    return bake; // () = CALLING the function, and no ()'s is essentially jsut the function expression
    
}

// here I'm reassinging the value of x not to the cake function, but the function that's actually 
// being called which is bake
 // This will invoke cake -- by invoking cake(), we are really calling bake();  Here we are losing cake()
// technically we are executing the INNER function.

x = x();

for (let i=0; i<5; i++) {
    x();
}

console.log(i);

