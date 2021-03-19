if (sessionStorage.getItem('currentUser') == null) {
    window.location.href = 'http://localhost:8080/project-1/resources/html/error.html'
} else if (sessionStorage.getItem('userRole') == null) {
    window.location.href = 'http://localhost:8080/project-1/resources/html/error.html'
}
fetchData();

function fetchData() {

    fetch('http://localhost:8080/project-1/getUser')
        .then(response => {

            if (!response.ok) {
                throw Error("ERROR");
            }

            return response.json();
        }).then(data => {
            console.log(data);

            if (data.status == 'process failed') {
                console.log('process failed')
                alert('Request failed, please try again!')
            } else if (data.status == 'unauthorized access') {
                console.log('unauthorized access')
                window.location.href = 'http://localhost:8080/project-1/resources/html/error.html'
            } else if (data.status == 'no record') {
                console.log('no record')
                alert('There is no employee in the system')
            } else {

                let userId = document.getElementById('app1');
                userId.innerHTML = `${data.userId}`;
                let username = document.getElementById('app2');
                username.innerHTML = `${data.username}`;
                let firstName = document.getElementById('app3');
                firstName.innerHTML = `${data.firstName}`;
                let lastName = document.getElementById('app4');
                lastName.innerHTML = `${data.lastName}`;
                let email = document.getElementById('app5');
                email.innerHTML = `${data.email}`;
                let role = document.getElementById('app6');
                role.innerHTML = `${data.role.role}`;
            }

        }).catch(error => {
            console.log(error);
            window.location.href = 'http://localhost:8080/project-1/resources/html/error.html'
        });
}

