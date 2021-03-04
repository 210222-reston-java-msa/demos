function sendLogin() {
	console.log("send login triggered");
    // save some variables here
    let uName = document.getElementById('uName').value;
    // do the same thing with pWord....
    let pWord = document.getElementById('pWord').value;

    console.log(`Username: ${uName}`);
    console.log(`Password: ${pWord}`);

    // building an obj literal with the user credentials
    let loginTemplate = {
        username: uName,
        password: pWord
    }


    // begin some AJAX workflow....

    // 1. get the XMLHttpRequest Object i.e ... let xhr = ....
    let xhr = new XMLHttpRequest();

    // 2. xhr.onreadystatechange
    xhr.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            console.log("success");

            sessionStorage.setItem('currentUser', this.responseText)

            window.location = "http://localhost:8080/EmployeeDBServlets/home.html";

            console.log(sessionStorage.getItem('currentUser'));
        }

        if (this.readyState === 4 && this.status === 204) { // 204 means NO CONTENT FOUND (but connection was made)

            console.log("failed to find user");

            let childDiv = document.getElementById('warningText');
            childDiv.textContent = "Failed to login!  Username of Password is incorrect"
        }
    }
    
    // 3. xhr.open("POST, "http:/localhost:8080/EmployeeDBServlet/url for the loginServlet")
    xhr.open("POST", "http://localhost:8080/EmployeeDBServlets/login")

    // 4. xhr.send();
    xhr.send(JSON.stringify(loginTemplate)) // this is converting out js object to JSON

}