/*
    AJAX stands for Asynchronous JavaScript And XML

        It allows for performing javascript actions asynchronously.
        Generally used in conjunction with http requests, as we can wait
        for the response in the background while still doing other things.

        One of the biggest advantages of JS is the responsiveness it provides
        to our webpages. We can easily respond to events on our frontend and manipulate
        the DOM using basic JS functions.

        But the magic JS does not end there. We can use JS to make asynchronous
        requests to servers. We do this with AJAX.
        We want our application to continue to be responsive, while waiting
        for the server to respond. By sending the request and receiving the
        response asynchronously, we accomplish this.

        Note that AJAX has XML in it's name, but that primarily has its origin in
        and older era, where XML was used far more frequently as a data interchange
        format. Nowadays, we primarily use JSON. There are still some places that
        use XML, but JSON is a bit more popular.

    JSON stands JavaScript Object Notation

        JSON is a standard data interchange format.  It sends human-readable text 
        to store and transmit data objects.  
        These objects have properties made of key-value pairs
*/

let button = document.getElementById("btn");
// add an event Listener to our button ("click", ajaxCall)
button.addEventListener("click", ajaxCall);

// Capture the <p> tag
// We will APPEND the Name and Id of the pokemon here
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