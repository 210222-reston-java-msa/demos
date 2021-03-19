if (sessionStorage.getItem('currentUser') == null) {
	window.location.href = 'http://localhost:8080/project-1/resources/html/error.html'
} else if (sessionStorage.getItem('userRole') == null) {
	window.location.href = 'http://localhost:8080/project-1/resources/html/error.html'
}

document.addEventListener('DOMContentLoaded', () => {
	document
		.getElementById('myForm')
		.addEventListener('submit', signup);
});

function signup(ev) {
	ev.preventDefault(); //stop the page reloading
	let userId = sessionStorage.getItem('currentUser');
	let username = document.getElementById("username").value;
	let password = document.getElementById("password").value;
	let confirmPassword = document.getElementById("confirmPassword").value;
	let firstName = document.getElementById("firstName").value;
	let lastName = document.getElementById("lastName").value;
	let email = document.getElementById("email").value;

	console.log(userId);
	console.log(username);
	console.log(password);
	console.log(confirmPassword);
	console.log(firstName);
	console.log(lastName);
	console.log(email);

	fetch('http://localhost:8080/project-1/updateUser', {
		method: 'POST',
		redirect: 'follow',
		credentials: 'include',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify({
			userId: userId,
			username: username,
			password: password,
			confirmPassword: confirmPassword,
			firstName: firstName,
			lastName: lastName,
			email: email
		})
	}).then(function (response) {

		if (!response.ok) {
			throw Error("ERROR");
		}

		return response.json();
	}).then(function (data) {
		console.log(data)

		document.getElementById("app").innerHTML = "";
		if (data.status == 'unauthorized access') {
			console.log('unauthorized access')
			window.location.href = 'http://localhost:8080/project-1/resources/html/error.html'			
		} else if (data.status == 'password not match') {
			console.log("request failed");
			alert('Password not match, please try again!')
		} else if (data.status == 'process failed') {
			console.log('process failed')
			alert('Process failed, please try again!')
		} else if (data.status == 'email is already used') {
			console.log('email is already used')
			alert('Email is already used!')
		} else if (data.status == 'username is already used') {
			console.log('username is already used')
			alert('Username is already used!')
		} else {
			console.log(data);
			html = `<p style="color:green;"><b> Your profile updated successfully.</b></p>`
			document.getElementById("app").innerHTML = "";
			document.getElementById("app").innerHTML = html;
			
			
		}

	}).catch(error => {
		console.log(error);
		window.location.href = 'http://localhost:8080/project-1/resources/html/error.html'
	})

}

