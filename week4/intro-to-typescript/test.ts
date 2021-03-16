function doSomething() {

    for(let i=0; i<5; i++) {

        console.log(i)
    }

    console.log("Finally i is " + i); // according to lexical/block scopes, I can't print i
    // The typescript compiler is telling us so
}

doSomething();