if (sessionStorage.getItem('currentUser') == null) {
    window.location.href = 'http://localhost:8080/project-1/resources/html/error.html'
} else if (sessionStorage.getItem('userRole') == null) {
    window.location.href = 'http://localhost:8080/project-1/resources/html/error.html'
}

let receiptId = sessionStorage.getItem('receiptId');
showImg(receiptId);

function showImg(val) {


    let userId = sessionStorage.getItem('currentUser');
    let reimbId = val;

    console.log(userId);
    console.log(reimbId);

    fetch('http://localhost:8080/project-1/getReceipt', {
        method: 'POST',
        redirect: 'follow',
        credentials: 'include',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            userId: userId,
            reimbId: reimbId
        })
    }).then(function (response) {

        if (!response.ok) {
            throw Error("ERROR");
        }

        return response.json();
    }).then(function (data) {
        console.log(data)
        console.log(data.base64);
        
        if (data.status == 'process failed') {
            console.log("request failed");
            alert('Request failed, please try again!')
        } else if (data.status == 'no record') {
            console.log('no record')
            alert('There is no receipt in the system')
        } else if (data.status == 'unauthorized access') {
            console.log('unauthorized access')
            window.location.href = 'http://localhost:8080/project-1/resources/html/error.html'
        } else {
             document.getElementById('img1').src = data.base64;
        
        }

    }).catch(error => {
        console.log(error);
        window.location.href = 'http://localhost:8080/project-1/resources/html/error.html'
    })

}

