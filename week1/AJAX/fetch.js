/*
    Fetch API:
        What is it? It's a  modern interface that alows yoou to make HTTP requests to servers
        from browsers.  It's simpler and cleaner thatn th xhr (XmlHTTPRequest Object)
*/

window.onload = function(){
    console.log('fetch.js is loaded');
    document.getElementById('btn').addEventListener("click", getPokemon);
    
}

// var pokemon;

function getPokemon(){

    let number = document.getElementById('field').value

    // The fetch() method requires 1 param: the url from which we are retrieving data

    // The fetch() method returns a promise.
    // a promise is an obj that produces a single value at some time in the future.

    // The beauty of promises is that it allows us to process them and do cool things like use the then()
    // or handle errors
    fetch(`https://pokeapi.co/api/v2/pokemon/${number}`)

    .then( (response) => response.json())
    .then( (data) => {

        console.log(data);

        setPokemonImg(data.sprites.front_default);

        // we're just demonstrating that we can now use this global var anywhere in our program
        // it's not necessary
        // pokemon = data;

    }, (err) => {
        console.log(`error: ${err}`)
    })
        
}

function setPokemonImg(imgSrc) {
    document.getElementById('pokemonImg').src = imgSrc;
}