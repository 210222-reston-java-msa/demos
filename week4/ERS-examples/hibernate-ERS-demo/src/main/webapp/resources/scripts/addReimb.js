if (sessionStorage.getItem('currentUser') == null) {
    window.location.href = 'http://localhost:8080/project-1/resources/html/error.html'
} else if (sessionStorage.getItem('userRole') == null) {
    window.location.href = 'http://localhost:8080/project-1/resources/html/error.html'
}

document.addEventListener('DOMContentLoaded', () => {
    document
        .getElementById('myForm')
        .addEventListener('submit', addReimb);
});

function addReimb(ev) {
    ev.preventDefault(); //stop the page reloading
    let amount = document.getElementById("amount").value;
    let description = document.getElementById("description").value;
    let typeId = document.getElementById("typeId").value;

    var myFile = document.getElementById("reimbImg");
    var file = myFile.files[0];
    var reader = new FileReader();

    reader.onloadend = function () {
      //  let base64 = reader.result.split(",")[1];
        let base64 = reader.result;
        console.log(base64);
        console.log(amount);
        console.log(description);
        console.log(typeId);
    
        fetch('http://localhost:8080/project-1/addReimb', {
            method: 'POST',
            redirect: 'follow',
            credentials: 'include',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                amount: amount,
                typeId: typeId,
                description: description,
                base64: base64,
                userId : sessionStorage.getItem('currentUser')
            })
        }).then(function (response) {

            if (!response.ok) {
                throw Error("ERROR");
            }

            return response.json(); 
        }).then(function (data) {

            if (data.status == 'process failed') {
                console.log("request failed");
                alert('Request failed, please try again!')
            } else if(data.status == 'unauthorized access'){
                console.log('unauthorized access')
                window.location.href = 'http://localhost:8080/project-1/resources/html/error.html'
            } else {
                console.log(data);
                html = `<p style="color:green;"><b> Reimbursement request # ${data.reimbId} submitted successfully.</b></p>`
            }
            
            document.getElementById("app").innerHTML = html;
    
        }).catch(error => {
            console.log(error);
            window.location.href = 'http://localhost:8080/project-1/resources/html/error.html'
        })

    }

    reader.readAsDataURL(file);

}


