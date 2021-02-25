window.onload = function() {

    console.log("starting up.....")

    //1. cache the DOM elements BEFORE we start manipulating them
    let $min = document.getElementById('min');
    let $minOut = document.getElementById('min-output');
    let $max = document.getElementById('max');
    let $maxOutput = document.getElementById('max-output');
    let $generate = document.getElementById('generate');

    // 2. add event listeners to each element
    $min.addEventListener('input', (evt) => {
            $minOut.textContent = evt.target.value;
    })
    
    $max.addEventListener('input', (evt) => {
        $maxOutput.textContent = evt.target.value;
    })

    $generate.addEventListener('click', () => {
        console.log('clicked!');
        let min = Number($min.value); // this ensures that my value is indeed a number so I can perform some arithmetic
        let max = Number($max.value); // this ensures that my value is indeed a number so I can perform some arithmetic
        
        // isert some control flow to make sure that min is < max BEFORE we calculate a random number
        if (min > max) {
            window.alert("Minimum must be less than maximum!!!")
            console.error('try again')
        } else {
            let output =  Math.floor(Math.random() * (max - min) + min); 
            console.log("generated number is " + output);
            // to make it appear on screen!

            // getElementsByClassName returns a list of elements
            // I must specify the index -- in this case the only element is at index[0]
            document.getElementsByClassName('output')[0].innerHTML = output;


            // alternatively, you could change this element's id to something
            // and use getElementById
        }

    })






}