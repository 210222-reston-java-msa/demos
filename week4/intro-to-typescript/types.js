var count = 5;
// We can't reassign variables to different types in typescript!
// count = 'apples'; This will make the compiler angry...
// TypseScript is strictly typed.
var a; // We're declaring the types that can be assigned to this variable
var b;
var c;
var d; // the any datta type allows us to give this var any type
// d = 98;
// d = "apples";
var f = [1, true, 'apple', false];
var g = [1, 2, 3, 4];
//console.log(f[0]) // == 1, f[2] == 'apple'
var ColorRed = 0;
var ColorBlue = 1;
var ColorGreen = 2;
// Enums allow us to define named constants
// TypseScript gives us Enums! (Like Java).
// Below, what I'm about to do is an easier way of doing the above
var Color;
(function (Color) {
    Color[Color["Red"] = 0] = "Red";
    Color[Color["Blue"] = 1] = "Blue";
    Color[Color["Green"] = 2] = "Green";
})(Color || (Color = {}));
;
var backgroundColor = Color.Blue;
