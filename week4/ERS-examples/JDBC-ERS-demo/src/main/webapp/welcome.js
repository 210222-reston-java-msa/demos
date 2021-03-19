/**
 * Functionality for the front view. Login or create an account
 */

window.onload = function(){
	loadWelcomeView();
}

function loadWelcomeView(){	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#welcomeView').html(xhr.responseText);
			$('#createAccount').on('click', createAccountView);		// Go to the create account partial
		}
	}
	xhr.open("GET", "login.welcomeView", true);
	xhr.send();
}

function createAccountView(){
	console.log('In createAccount in welcome.js');
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#welcomeView').html(xhr.responseText);			// Load the create account view
			$('#loginPage').on('click', loadWelcomeView);		// Go back to login
			$('#createAccount').on('click', createAccount);		// Create an account
		}
	}
	xhr.open("GET", "create-account.welcomeView", true);
	xhr.send();	
}

function createAccount(){
	var r = $('#uTypeSelect').val();
	var first = $('#inputFirst').val();
	var last = $('#inputLast').val();
	var e = $('#inputEmail').val();
	var pw = $('#inputPassword').val();
	var repw = $('#reInputPassword').val();
	if (pw != repw){
		$('#error').html('Passwords do not match.');
		$('#inputPassword').val('');
		$('#reInputPassword').val('');
	}	// Input validation needed here because not using a form submit new account info...
	else if(r=='' || first==''|| last==''|| e==''|| pw=='' || repw==''){
		$('#error').html('Please fill out all fields.');				
	}
	else{
		var obj = {
				email: e,
				pwd: pw,
				firstName: first,
				lastName: last,
				roll: r
		}
		var user = JSON.stringify(obj);
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4){
				if (xhr.responseText == "null"){
					$('#error').html('An account with this email address already exists.');
				}
				else{
					alert("Your account is awaiting manager approval...")
					loadWelcomeView();
				}
			}
		}
		xhr.open("POST", "create-account");
		xhr.setRequestHeader("Content-Type", "application/json");
		xhr.send(user);		
	}
}

// Tells user that their account must be approved before login
function loadAccountCreatedView(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#welcomeView').html(xhr.responseText);
		}
	}
	xhr.open("GET", "account-created.welcomeView", true);
	xhr.send();	
}





