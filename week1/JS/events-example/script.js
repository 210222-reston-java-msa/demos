// 1. ccreate a function called Change the text
    // in which  I take in some input, and change it to "Changed Text"\

function changeTheText() {
    // Get the element by it's id
    var e = document.getElementById("my_paragraph");

    // change the element's text!
    e.textContent = "Changed Text!" // another way is e.innerHTML
}

//2.  we will grab the button element FROM the document, and then add an eventListener
// so, when we click the button, we change the text (thus invoking our custom function)
var btn = document.querySelector("input")

// you could also use 
//   var myButton = document.getElementById("my_button")
//   var anotherBUtton = document.getElementsByClassName // more 

btn.addEventListener("click", changeTheText) // we are passing a function as a parameter (this is a callback function)


// this isn't necessary]
console.log(document);