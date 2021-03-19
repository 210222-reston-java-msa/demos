/**
 * 
 */
window.onload = function(){
	loadUser();
	loadFrontView();
	$('#homeNav').on('click', loadFrontView);
	$('#pastNav').on('click', loadPastView);
	$('#resolvedNav').on('click', loadResolvedView);
	$('#employeeNav').on('click', loadEmployeesView);
	$('#pendingUsersNav').on('click', loadPendingEmployeesView);
}

var user;	// declare user here so it is not undefined elsewhere
function loadUser(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			user = JSON.parse(xhr.responseText);
			console.log(user);
			$('#welcomeMessage').html('<i>Welcome, ' + user.firstName + '. Here are all pending reimbursement requests.' +
			'<br>Expand a request to approve or deny</i>');
		}
	}
	xhr.open("POST", "user-servlet", true);		// Create a data transaction object if time...
	xhr.send();	
}

///////////////////////////////////////////////////////// FORMAT CHILD ROWS /////////////////////////////////////////////////////////////////
//Format child rows for Pending table
function formatPending(d, index){
	// `d` is the original data object for the row
	// child rows for pending reimbursements
	if (d.rStatus == 1){
		return '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">' +
		'<tr>' +
		'<td>Description:</td>' +
		'<td>' + d.rDesc + '</td>' +
		'</tr>' +
		'<tr>' +
		'<td id="resolve' +d.rId+ '"><button onclick="approveRequest('+d.rId+','+index+')" type="button" class="btn btn-success btn-sm">Approve</button>&nbsp&nbsp' +
		'<button onclick="denyRequest(' +d.rId+','+index+ ')" type="button" class="btn btn-danger btn-sm">Deny</button></td>' +
		'</tr>' +
		'</table>'; 
	}
	// child rows for resolved
	else if (d.rStatus == 2){
		return '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">' +
		'<tr>' +
			'<td>Description:</td>' +
			'<td>' + d.rDesc + '</td>' +
		'</tr>' +
		'<tr>' +
			'<td><i>Request Approved</i></td>' +
		'</tr>' +
		'</table>'; 
	}
	else {
		return '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">' +
		'<tr>' +
		'<td>Description:</td>' +
		'<td>' + d.rDesc + '</td>' +
		'</tr>' +
		'<tr>' +
		'<td><i>Request Denied</i></td>' +
		'</tr>' +
		'</table>';
	}
}
//Format child rows for Past and Personally Resolved table
function format(d){
	// `d` is the original data object for the row
	// Child rows for personally resolved
	console.log(d.rResolver);
	if (d.rResolver == (user.firstName + " " + user.lastName + " (<a href=\"#\"><i>" + user.email + "</i></a>)")){
		return '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">' +
		'<tr>' +
			'<td>Description:</td>' +
			'<td>' + d.rDesc + '</td>' +
		'</tr>' +

		'<tr>' +
			'<td>Resolved By <b>You</b>:</td>' +
			'<td>' + d.resolveDate + '</td>' +
		'</tr>' +       
		'</table>';
	}
	// child rows for all past
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
		'</table>'; 
	}
}

function formatResolved(d){
	return '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">' +
	'<tr>' +
		'<td>Description:</td>' +
		'<td>' + d.rDesc + '</td>' +
	'</tr>' +      
	'</table>'; 
}

function formatPendingEmployees(d, index){
	// `d` is the original data object for the row
	// child rows for pending reimbursements
	if (d.approved == 0){
		return '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">' +
		'<tr>' +
		'<td id="adEmp' +d.userId+ '"><button onclick="approveEmp('+d.userId+','+index+')" type="button"' + 
		'class="btn btn-success btn-sm">Approve</button>&nbsp&nbsp' +
		'<button onclick="denyEmp(' +d.userId+','+index+ ')" type="button" class="btn btn-danger btn-sm">Deny</button></td>' +
		'</tr>' +
		'</table>';
	}
	else if (d.approved == 1){
		return '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">' +
		'<tr>' +
		'<td><i>Employee Approved</i></td>' +
		'</tr>' +
		'</table>';
	}
	else if (d.approved == 3){
		return '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">' +
		'<tr>' +
		'<td><i>Employee Denied</i></td>' +
		'</tr>' +
		'</table>';
	}
	
		 

}
/////////////////////////////////////////////////////////////// APPROVE/DENY //////////////////////////////////////////////////////////////
function approveRequest(id, index){
	var obj ={
			rId : id
	}
	var idToApprove = JSON.stringify(obj);
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#resolve' + id).html('<i>Request Approved</i>');
			rdata[index].rStatus = 2;		// Change the status in rData so it displays correctly in the table
		}		
	}
	xhr.open("POST", "approve-request", true);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.send(idToApprove);		
}
function denyRequest(id, index){
	var obj ={
			rId : id
	}
	var idToDeny = JSON.stringify(obj);
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#resolve' + id).html('<i>Request Denied</i>');
			rdata[index].rStatus = 3;		// Change the status in rData so it displays correctly in the table
		}		
	}
	xhr.open("POST", "deny-request", true);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.send(idToDeny);		
}

function approveEmp(id, index){
	var obj ={
			userId : id
	}
	var idToApprove = JSON.stringify(obj);
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#adEmp' + id).html('<i>Employee Approved</i>');
			edata[index].approved = 1;		// Change the status in rData so it displays correctly in the table
			console.log(edata);
		}		
	}
	xhr.open("POST", "approve-employee", true);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.send(idToApprove);	
}
function denyEmp(id, index){
	var obj ={
			userId : id
	}
	var idToApprove = JSON.stringify(obj);
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#adEmp' + id).html('<i>Employee Denied</i>');
			edata[index].approved = 3;		// Change the status in rData so it displays correctly in the table
			console.log(edata);
		}		
	}
	xhr.open("POST", "deny-employee", true);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.send(idToApprove);	
}

//////////////////////////////////////////////////////////////// FORMAT TABLES ////////////////////////////////////////////////////////////////
//Reimbursement Type callback function
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
	xhr.open("GET", "r-type", false);	// Create a data transfer object instead if time permits...
	xhr.send();
}

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

function formatRoll(edata){
	for (let e of edata){
		var roll = e.roll;
		switch(roll){
		case 1: e.roll = "Employee"; break;
		case 2: e.roll = "Manager"; break;
		}
	}
}

function getEmployeeName(rdata){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			let employees = JSON.parse(xhr.responseText);
			for (r of rdata){
				for (e of employees){
					if (r.author == e.userId){
						r.author = e.firstName + " " + e.lastName;
						break;
					}
				}
			}
		}
	}
	xhr.open("GET", "user-servlet", false);
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

///////////////////////////////////////////////////////////////// FRONT VIEW ////////////////////////////////////////////////////////////////
function loadFrontView(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#managerView').html(xhr.responseText);
			if (user != undefined){
				$('#welcomeMessage').html('<i>Welcome, ' + user.firstName + '. Here are all pending reimbursement requests.' +
				'<br>Expand a request to approve or deny</i>');
			}
			loadPending(pendingCallback);
		}
	}
	xhr.open("GET", "front.managerView", true);
	xhr.send();
}

function pendingCallback(pending){
	rdata = pending;
	// Manipulate reimbursement objects to dynamically load display info
	getRType(rdata, typeCallback);
	formatAmount(rdata);
	getEmployeeName(rdata);
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
			{ "data": "author" },
			{ "data": "rType" },
			{ "data": "amount" },
			{ "data": "submitDate"}
			],
			"order": [[4, 'desc']]
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
			var index = row[0][0];	// This is the index of the table row, which corresponds to rdata array!
			row.child(formatPending(row.data(), index)).show();	// Pass the index into formatPending()
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
			if(callback) callback(pending);		// if statement checks if function param exists
		}
	}
	xhr.open("POST", "get-all-pending");
	xhr.send();
}

/////////////////////////////////////////////////////////////////PAST VIEW ////////////////////////////////////////////////////////////////////////
function loadPastView(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#managerView').html(xhr.responseText);
			loadPast(pastCallback);
		}
	}
	xhr.open("GET", "past.managerView", true);
	xhr.send();	
}

function pastCallback(past){
	rdata = past;
	getEmployeeName(rdata);
	getManagerName(rdata);
	getRType(rdata, typeCallback);
	formatAmount(rdata);
	formatStatus(rdata);
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
			{ "data": "author" },
			{ "data": "rType" },
			{ "data": "amount" },
			{ "data": "submitDate"},
			{ "data": "resolveDate"},
			{ "data": "rStatus"}
			],
			"order": [[5, 'desc']]
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

function loadPast(callback){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var past = JSON.parse(xhr.responseText);
			if(callback) callback(past);		// if statement checks if function param exists
		}
	}
	xhr.open("POST", "get-all-past", true);
	xhr.send();
}

//////////////////////////////////////////////////// PERSONALLY RESOLVED VIEW //////////////////////////////////////////////////////////////////
function loadResolvedView(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#managerView').html(xhr.responseText);
			loadResolved(resolvedCallback);
		}
	}
	xhr.open("GET", "resolved.managerView", true);
	xhr.send();	
}

function resolvedCallback(resolved){
	rdata = resolved;
	getRType(rdata, typeCallback);
	formatAmount(rdata);
	formatStatus(rdata);
	getEmployeeName(rdata);
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
			{ "data": "author" },
			{ "data": "rType" },
			{ "data": "amount" },
			{ "data": "submitDate"},
			{ "data": "resolveDate"},
			{ "data": "rStatus"}
			],
			"order": [[5, 'desc']]
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
			row.child(formatResolved(row.data())).show();
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

function loadResolved(callback){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var resolved = JSON.parse(xhr.responseText);
			if(callback) callback(resolved);		// if statement checks if function param exists
		}
	}
	xhr.open("POST", "get-past-by-resolver", true);
	xhr.send();
}

//////////////////////////////////////////////////// ALL EMPLOYEES VIEW //////////////////////////////////////////////////////////////////////
function loadEmployeesView(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#managerView').html(xhr.responseText);
			loadEmployees(employeesCallback);
		}
	}
	xhr.open("GET", "employees.managerView", true);
	xhr.send();	
}

function employeesCallback(employees){
	var count = 0;
	edata = employees;
	// Manipulate reimbursement objects to dynamically load types
	formatRoll(edata);
	var table = $("#employees").DataTable({
		"data": edata,
		retrieve: true,
		select:"single",
		"columns": [
			{
				"className": 'details-control',
				"orderable": false,
				"data": null,
				"defaultContent": '',
				"render": function () {
					return ++count;
				},
				width:"10px"
			},
			{ "data": "lastName" },
			{ "data": "firstName" },
			{ "data": "email" },
			{ "data": "roll"}
			],
			"order": [[1, 'asc']]
	});	
}

function loadEmployees(callback){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var employees = JSON.parse(xhr.responseText);
			if(callback) callback(employees);		// if statement checks if function param exists
		}
	}
	xhr.open("GET", "get-approved-employees", true);
	xhr.send();
}

//////////////////////////////////////////////////// PENDING EMPLOYEES VIEW //////////////////////////////////////////////////////////////////
function loadPendingEmployeesView(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#managerView').html(xhr.responseText);
			loadPendingEmployees(pendingEmployeesCallback);
		}
	}
	xhr.open("GET", "pendingUsers.managerView", true);
	xhr.send();	
}

function pendingEmployeesCallback(pendingEmp){
	edata = pendingEmp;
	// Manipulate reimbursement objects to dynamically load types
	formatRoll(edata);
	var table = $("#pendingEmployees").DataTable({
		"data": edata,
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
			{ "data": "lastName" },
			{ "data": "firstName" },
			{ "data": "email" },
			{ "data": "roll"}
			],
			"order": [[1, 'asc']]
	});	
//	Add event listener for opening and closing details
	$('#pendingEmployees tbody').on('click', 'td.details-control', function () {
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
			var index = row[0][0];	// This is the index of the table row, which corresponds to rdata array
			row.child(formatPendingEmployees(row.data(), index)).show();	// Pass the index into formatPending()
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

function loadPendingEmployees(callback){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var pendingEmp = JSON.parse(xhr.responseText);
			if(callback) callback(pendingEmp);		// if statement checks if function param exists
		}
	}
	xhr.open("GET", "get-pending-employees", true);
	xhr.send();	
}





