document.addEventListener('DOMContentLoaded', () => {
    document
        .getElementById('myForm')
        .addEventListener('submit', forgotPass);
});

function forgotPass(ev) {
    ev.preventDefault(); //stop the page reloading
    let email = document.getElementById("email").value;

    console.log(email);
    
    fetch('http://localhost:8080/project-1/forgotPass', {
        method: 'POST',
        redirect: 'follow',
        credentials: 'include',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            email: email
        })
    }).then(function (response) {

        if(!response.ok){
            throw Error("ERROR")
        }

        return response.json(); 
    }).then(function (data) {
        console.log(data)

        if (data.status == 'process failed') {
            console.log("process failed");
            let childDiv= document.getElementById("warningText")
            childDiv.innerHTML =`<p style="color:red;"><b>This email adress invalid!</b></p>`;
        } else {
            console.log("email sent successfully");
            let childDiv= document.getElementById("warningText")
            childDiv.innerHTML =`<p style="color:green;"><b>We emailed your password!</b></p>`;
        }
    }).catch(error => {
        console.log(error);
        // warning message later...
    })
}