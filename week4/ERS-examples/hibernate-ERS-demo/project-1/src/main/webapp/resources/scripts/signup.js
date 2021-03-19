document.addEventListener('DOMContentLoaded', () => {
	document
		.getElementById('myForm')
		.addEventListener('submit', signup);
});

function signup(ev) {
	ev.preventDefault(); //stop the page reloading
	let username = document.getElementById("uname").value;
	let password = document.getElementById("pass").value;
	let confirmPassword = document.getElementById("cpass").value;
	let firstName = document.getElementById("fname").value;
	let lastName = document.getElementById("lname").value;
	let email = document.getElementById("email").value;

	console.log(username);
	console.log(password);
	console.log(confirmPassword);
	console.log(firstName);
	console.log(lastName);
	console.log(email);

	fetch('http://localhost:8080/project-1/signup', {
		method: 'POST',
		redirect: 'follow',
		credentials: 'include',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify({
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


		if (data.status == 'password not match') {
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
			alert("Your account created successfully");
			console.log('success')
			window.location.href = 'http://localhost:8080/project-1/index.html'
		}

	}).catch(error => {
		console.log(error);
		//warning message later...
	})

}

