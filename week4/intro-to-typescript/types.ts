var count = 5;

// We can't reassign variables to different types in typescript!
// count = 'apples'; This will make the compiler angry...

// TypseScript is strictly typed.

let a: number; // We're declaring the types that can be assigned to this variable
let b: boolean;
let c: string;
let d: any; // the any datta type allows us to give this var any type
// d = 98;
// d = "apples";
let f: any[] = [1, true, 'apple', false];
let g: number[] = [1, 2, 3, 4];

//console.log(f[0]) // == 1, f[2] == 'apple'

const ColorRed = 0;
const ColorBlue = 1;
const ColorGreen = 2;

// Enums allow us to define named constants
// TypseScript gives us Enums! (Like Java).
// Below, what I'm about to do is an easier way of doing the above

enum Color {Red, Blue, Green};

let backgroundColor = Color.Blue;

// TypeScript is transpiled into ES5 (not ES6) so that we're sure it'll run in every browser








