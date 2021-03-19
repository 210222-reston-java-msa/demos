if (sessionStorage.getItem('currentUser') == null) {
    window.location.href = 'http://localhost:8080/project-1/resources/html/error.html'
} else if (sessionStorage.getItem('userRole') == null) {
    window.location.href = 'http://localhost:8080/project-1/resources/html/error.html'
}

document.addEventListener('DOMContentLoaded', () => {
    document
        .getElementById('myForm')
        .addEventListener('submit', feedback);
});

function feedback(ev) {
    ev.preventDefault(); //stop the page reloading
    let subject = document.getElementById("subject").value;
    let description = document.getElementById("description").value;
    let typeId = document.getElementById("typeId").value;


    console.log(subject);
    console.log(description);
    console.log(typeId);

    fetch('http://localhost:8080/project-1/feedback', {
        method: 'POST',
        redirect: 'follow',
        credentials: 'include',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            subject: subject,
            typeId: typeId,
            description: description,
            userId: sessionStorage.getItem('currentUser')
        })
    }).then(function (response) {

        if (!response.ok) {
            throw Error("ERROR");
        }

        return response.json();
    }).then(function (data) {

        console.log(data);
        
        if (data.status == 'process failed') {
            console.log("request failed");
            alert('Request failed, please try again!')
        } else if (data.status == 'unauthorized access') {
            console.log('unauthorized access')
            window.location.href = 'http://localhost:8080/project-1/resources/html/error.html'
        } else {
            console.log(data);
            alert('Thank you for the feedback. We will get back to you soon');
        }


    }).catch(error => {
        console.log(error);
    //    window.location.href = 'http://localhost:8080/project-1/resources/html/error.html'
    })

}





