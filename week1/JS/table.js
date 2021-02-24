// 1. create an array of objects (each obj will have a name, color, cheese)
var arr = [
    {
        firstName: 'Vision',
        color: 'purple',
        cheese: 'Bleu'
    },
    {
        firstName: 'Wanda',
        color: 'red',
        cheese: 'Pepperjack'
    },
    {
        firstName: 'Agnes',
        color:'yellow',
        cheese: 'Cheddar'
    }
];


// 2. create a function to populate our table with each obj inside our arr

function populateTable(someArray) {

    var table = document.getElementById("myTable");

    someArray.forEach( (obj) => {

        // 1. for each object create a new row (<tr>) and stick it onto (append) the table that exists
        let tr = document.createElement("tr");
        table.appendChild(tr);

        // 2. for each obj enter some data (<td>)
        let td = document.createElement("td");
            // - firstName <td>
            tr.appendChild(td);
            td.innerHTML = obj.firstName;
            // - color <td>;
            td = document.createElement("td");
            tr.appendChild(td);
            td.innerHTML = obj.color;
            // how to change td's bagkrounnd color of THIS CELL ONLY to reflect the object's color property
            td.style.backgroundColor=obj.color;

            // - cheese <td>;
            td = document.createElement("td");
            tr.appendChild(td);
            td.innerHTML = obj.cheese;
        });
}

window.onload = populateTable(arr);