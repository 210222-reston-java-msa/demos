/*
    JSON = JavaScript Object Notation
        JSON is a standard data interchange format.  It sends human-readable text 
        to store and transmit data objects.  
        These objects have properties made of key-value pairs
*/

let button = document.getElementById("btn");
// add an event Listener to our button ("click", ajaxCall)
button.addEventListener("click", ajaxCall);

// capture the p tag
let input = document.getElementById("input")


// define a function for WHAT our ajax call actually does

function ajaxCall() {
    
    let number = document.getElementById("field").value;
    // get this number and append it to the url

    // STEP 1
    let xhr = new XMLHttpRequest();
    // This oiobj is used for asynchronous requests to a server

  /*
    A readyState is a property which signifies that state that
    the request is currently in.
    There are several states:

    0. UNSENT : open() has not yet been called
    1. OPENED : open() has been called
    2. HEADERS_RECEIVED : send() has been called, and the headers of
        the response as well as the status are now available
    3. LOADING : Downloading the response. responseText (the xhr property) holds
        partial data
    4. DONE : The entire operation is now complete
  */
    
    // STEP 2
    xhr.onreadystatechange = function() {

        if(this.readyState == 4  && this.status == 200) {
            
            let data = JSON.parse(xhr.responseText)

            console.log(data);
        }
    }


    // STEP 3
    // open the request
    xhr.open()
    // this is the request to the server: includes the method and the url

}