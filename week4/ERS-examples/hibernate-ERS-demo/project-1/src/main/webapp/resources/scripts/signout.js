if(sessionStorage.getItem('currentUser') == null){
	window.location.href = 'http://localhost:8080/project-1/index.html'
}

fetchData();

function fetchData() {

    fetch('http://localhost:8080/project-1/signout', {
        method: 'POST',
        redirect: 'follow',
        credentials: 'include',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            userId: sessionStorage.getItem('currentUser')
        })
    }).then(function (response) {

        if (!response.ok) {
            throw Error("ERROR");
        }

        return response.json();
    }).then(function (data) {
        console.log(data)

        if (data.loginStatus == 'fail') {
            console.log("login failed");
            window.location.href = 'http://localhost:8080/project-1/index.html'
        } else {
            sessionStorage.removeItem('currentUser');
            sessionStorage.removeItem('userRole');
            sessionStorage.clear();
            window.location.href = 'http://localhost:8080/project-1/index.html'
        }

    }).catch(error => {
        console.log(error);
        sessionStorage.removeItem('currentUser');
        sessionStorage.removeItem('userRole');
        sessionStorage.clear();
        window.location.href = 'http://localhost:8080/project-1/index.html'
    });


}