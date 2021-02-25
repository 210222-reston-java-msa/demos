/*
Javascript has a special way of coercing all different values into booleans

We would say that a value coerced into true = truthy and value coerced into false = falsey.
*/

/*
    There are only a couple falsey values, and everything else is truthy.
    The falsey values are:
    - 0
    - null,
    - empty string,
    - false
    - NaN
    - undefined


    !! (double bang operator) returns a variables truthy value. !! asks the question: "is it truthy?""
*/

function checkTruthy(input) {

    console.log(`
        input is ${input} and is of type ${typeof(input)}
        It has a truthy value of ${!!input}
    `) // the !!input will return true or false
}

checkTruthy(0);
checkTruthy(null);
checkTruthy("");
checkTruthy(false);
checkTruthy(NaN);
checkTruthy(undefined);

console.log("TRUTHY VALUES")

checkTruthy("GME");
checkTruthy(15);
var y = 0;
checkTruthy(y);
checkTruthy({}); // and emoty object is still a valid object, it's like [].  It's NOT null, or undefined

var username = '';
checkTruthy(username);