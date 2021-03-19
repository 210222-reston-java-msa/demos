/**
 * onload loads employee home view and initiates user as a global variable
 */
window.onload = function(){
	loadUser();
	loadFrontView();
	$('#homeNav').on('click', loadFrontView);
	$('#allNav').on('click', loadAllView);
	$('#pastNav').on('click', loadPastView);
	$('#submitNav').on('click', loadSubmitView);
}

var user;	// declare user here so it is not undefined elsewhere
function loadUser(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			user = JSON.parse(xhr.responseText);
			console.log(user)
//			if (user == null){
//				logout();
//			}
			// String interpolation doesn't work?
//			$('#welcomeMessage').html(`Welcome, ${user.firstName}. Here are your pending reimbursement requests.`);
			$('#welcomeMessage').html('<i>Welcome, ' + user.firstName + '. Here are your pending reimbursement requests.</i>');
		}
	}
	xhr.open("POST", "user-servlet", true);
	xhr.send();	
}

function logout(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			console.log("in logout()");
		}
	}
	xhr.open("POST", "logout", true);
	xhr.send();
}

// Format's child rows. Used for all tables
function format(d){   
	// `d` is the original data object for the row
	// child rows for pending reimbursements
	if (d.rStatus == 1 || d.rStatus == "<i>Pending...</i>"){
		return '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">' +
		'<tr>' +
		'<td>Description:</td>' +
		'<td>' + d.rDesc + '</td>' +
		'</tr>' +
		'</table>'; 
	}
	// child rows for approved/denied reimbursements
	else{
		return '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">' +
		'<tr>' +
		'<td>Description:</td>' +
		'<td>' + d.rDesc + '</td>' +
		'</tr>' +

		'<tr>' +
		'<td>Resolved By:</td>' +
		'<td>' + d.rResolver + '</td>' +
		'</tr>' +
		'<tr>' +
		'<td>Resolve Date:</td>' +
		'<td>' + d.resolveDate + '</td>' +
		'</tr>' +
		'</table>'; 
	}
}

//////////////////////////////////////////////////////// FRONT VIEW //////////////////////////////////////////////////////////
function loadFrontView(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#employeeView').html(xhr.responseText);
			if (user != undefined){
				$('#welcomeMessage').html('<i>Welcome, ' + user.firstName + '. Here are your pending reimbursement requests.</i>');	
			}
			loadPending(pendingCallback);
		}
	}
	xhr.open("GET", "front.employeeView", true);
	xhr.send();
}

// CODE TO POPULATE PENDING TABLE GOES HERE! Called by loadPending(callback) in loadFrontView() in window.onload();
function pendingCallback(pending){
	rdata = pending;
	// Manipulate reimbursement objects to dynamically load display info
	getRType(rdata, typeCallback)
	formatAmount(rdata);
	console.log(rdata);
	var table = $('#pending').DataTable({
		"data": rdata,
		retrieve: true,
		select:"single",
		"columns": [
			{
				"className": 'details-control',
				"orderable": false,
				"data": null,
				"defaultContent": '',
				"render": function () {
					return '<i class="fa fa-plus-square" aria-hidden="true"></i>';
				},
				width:"15px"
			},
			{ "data": "submitDate" },
			{ "data": "rType" },
			{ "data": "amount" },
			],
			"order": [[1, 'desc']]
	});
//	Add event listener for opening and closing details
	$('#pending tbody').on('click', 'td.details-control', function () {
		var tr = $(this).closest('tr');
		var tdi = tr.find("i.fa");
		var row = table.row(tr);

		if (row.child.isShown()) {
			// This row is already open - close it
			row.child.hide();
			tr.removeClass('shown');
			tdi.first().removeClass('fa-minus-square');
			tdi.first().addClass('fa-plus-square');
		}
		else {
			// Open this row
			row.child(format(row.data())).show();
			tr.addClass('shown');
			tdi.first().removeClass('fa-plus-square');
			tdi.first().addClass('fa-minus-square');
		}
	});

	table.on("user-select", function (e, dt, type, cell, originalEvent) {
		if ($(cell.node()).hasClass("details-control")) {
			e.preventDefault();
		}
	});	
	
}

function loadPending(callback){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var pending = JSON.parse(xhr.responseText);
			if(callback) callback(pending);		// if statement checks if function param exists?
		}
	}
	xhr.open("POST", "get-pending-by-author");
	xhr.send();
}

//////////////////////////////////////////////////////////////////// FORMAT TABLES ///////////////////////////////////////////////////////////////////////////
function formatAmount(rdata){
	for (let r of rdata){
		r.amount = "$" + (r.amount).toFixed(2);
	}
}

function formatStatus(rdata){
	for (let r of rdata){
		var status = r.rStatus;
		switch(status){
		case 1: r.rStatus = "<i>Pending...</i>"; break;
		case 2: r.rStatus = "Approved"; break;
		case 3: r.rStatus = "Denied"; break;
		}
	}
}

// Reimbursement Type callback function
function typeCallback(data, types){
	for (let r of data){
		r.rType = (types[r.rType-1]).type;
	}
	rdata = data;
	console.log(rdata);
}
function getRType(rdata, callback){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			let types = JSON.parse(xhr.responseText);
			if(callback) callback(rdata, types);
		}
	}
	xhr.open("GET", "r-type", false);	// ...Create a data transfer object instead if time permits...
	xhr.send();
}

function getManagerName(rdata){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			let managers = JSON.parse(xhr.responseText);
			console.log(managers)
			for (r of rdata){
				for (m of managers){
					if (r.rResolver == m.userId){
						r.rResolver = m.firstName + " " + m.lastName + " (<a href=\"#\"><i>" + m.email + "</i></a>)";
						break;
					}
				}
			}
		}
	}
	xhr.open("GET", "user-servlet", false);
	xhr.send();
}


//////////////////////////////////////////////////////// ALL VIEW /////////////////////////////////////////////////////////////
function loadAllView(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#employeeView').html(xhr.responseText);
			loadAll(allCallback);
		}
	}
	xhr.open("GET", "all.employeeView", true);
	xhr.send();	
}
// POPULATE 'ALL' TABLE
function allCallback(all){
	var rdata = all;
	// Manipulate reimbursement objects to dynamically load types
	getRType(rdata, typeCallback)
	formatAmount(rdata);
	formatStatus(rdata);
	getManagerName(rdata);
	var table = $('#all').DataTable({
		"data": rdata,
		retrieve: true,
		select:"single",
		"columns": [
			{
				"className": 'details-control',
				"orderable": false,
				"data": null,
				"defaultContent": '',
				"render": function () {
					return '<i class="fa fa-plus-square" aria-hidden="true"></i>';
				},
				width:"15px"
			},
			{ "data": "submitDate" },
			{ "data": "rType" },
			{ "data": "amount" },
			{ "data": "rStatus"}
			],
			"order": [[1, 'desc']]
	});
//	Add event listener for opening and closing details
	$('#all tbody').on('click', 'td.details-control', function () {
		var tr = $(this).closest('tr');
		var tdi = tr.find("i.fa");
		var row = table.row(tr);

		if (row.child.isShown()) {
			// This row is already open - close it
			row.child.hide();
			tr.removeClass('shown');
			tdi.first().removeClass('fa-minus-square');
			tdi.first().addClass('fa-plus-square');
		}
		else {
			// Open this row
			row.child(format(row.data())).show();
			tr.addClass('shown');
			tdi.first().removeClass('fa-plus-square');
			tdi.first().addClass('fa-minus-square');
		}
	});

	table.on("user-select", function (e, dt, type, cell, originalEvent) {
		if ($(cell.node()).hasClass("details-control")) {
			e.preventDefault();
		}
	});	
}

function loadAll(callback){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var all = JSON.parse(xhr.responseText);
			if(callback) callback(all);		// if statement checks if function param exists
		}
	}
	xhr.open("POST", "get-all-by-author", true);
	xhr.send();
}


//////////////////////////////////////////////////////// PAST VIEW /////////////////////////////////////////////////////////////////
function loadPastView(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#employeeView').html(xhr.responseText);
			loadPast(pastCallback);
		}
	}
	xhr.open("GET", "past.employeeView", true);
	xhr.send();	
}
//POPULATE 'ALL' TABLE
function pastCallback(all){
	var rdata = all;
	// Manipulate reimbursement objects to dynamically load types
	getRType(rdata, typeCallback)
	formatAmount(rdata);
	formatStatus(rdata);
	getManagerName(rdata);
	var table = $('#past').DataTable({
		"data": rdata,
		retrieve: true,
		select:"single",
		"columns": [
			{
				"className": 'details-control',
				"orderable": false,
				"data": null,
				"defaultContent": '',
				"render": function () {
					return '<i class="fa fa-plus-square" aria-hidden="true"></i>';
				},
				width:"15px"
			},
			{ "data": "submitDate" },
			{ "data": "rType" },
			{ "data": "amount" },
			{ "data": "rStatus"}
			],
			"order": [[1, 'desc']]
	});
//	Add event listener for opening and closing details
	$('#past tbody').on('click', 'td.details-control', function () {
		var tr = $(this).closest('tr');
		var tdi = tr.find("i.fa");
		var row = table.row(tr);

		if (row.child.isShown()) {
			// This row is already open - close it
			row.child.hide();
			tr.removeClass('shown');
			tdi.first().removeClass('fa-minus-square');
			tdi.first().addClass('fa-plus-square');
		}
		else {
			// Open this row
			row.child(format(row.data())).show();
			tr.addClass('shown');
			tdi.first().removeClass('fa-plus-square');
			tdi.first().addClass('fa-minus-square');
		}
	});

	table.on("user-select", function (e, dt, type, cell, originalEvent) {
		if ($(cell.node()).hasClass("details-control")) {
			e.preventDefault();
		}
	});	
}

function loadPast(callback){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var all = JSON.parse(xhr.responseText);
			if(callback) callback(all);		// if statement checks if function param exists
		}
	}
	xhr.open("POST", "get-past-by-author", true);
	xhr.send();
}




//////////////////////////////////////////////////////// SUBMIT VIEW /////////////////////////////////////////////////////////////
function loadSubmitView(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#employeeView').html(xhr.responseText);
			$('#cancelSubmit').on('click', loadFrontView);
			$('#submitRequest').on('click', submitRequest)
			loadRTypes();
		}
	}
	xhr.open("GET", "submit.employeeView", true);
	xhr.send();		
}

function loadRTypes(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var types = JSON.parse(xhr.responseText);
			console.log(types);	
			for (t of types){
				var opt = document.createElement("option");
				opt.textContent = t.type;
				opt.value = t.id;
				$('#rTypeSelect').append(opt);
			}			
		}		
	}
	xhr.open("GET", "r-type", true);	
	xhr.send();
}

function submitRequest(){
	var t = $('#rTypeSelect').val();
	t = Number(t);
	var a = $('#amount').val();
	a = Number(a);
	var d = $('#description').val();
	var obj = {
			amount: a,
			rType: t,
			rDesc: d
	}
	var incomplete = JSON.stringify(obj);
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4){
			loadSubmittedView(t,a,d);
		}
	}
	xhr.open("POST", "submit-request");
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.send(incomplete);
}


function loadSubmittedView(t,a,d){
	console.log("In loadSubmittedView()");
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#employeeView').html(xhr.responseText);
			console.log("are these variables being passed?");
			a = "$" + a.toFixed(2);
			$('#submitMessage').html("<i>Your </i>" + a + " <i>reimbursement request for</i> \"" + d + "\" <i>is awaiting manager approval...</i?");
			
		}
	}
	xhr.open("GET", "request-submitted.employeeView", true);
	xhr.send();	
}

















