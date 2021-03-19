if (sessionStorage.getItem('currentUser') == null) {
    window.location.href = 'http://localhost:8080/project-1/resources/html/error.html'
} else if (sessionStorage.getItem('userRole') == null) {
    window.location.href = 'http://localhost:8080/project-1/resources/html/error.html'
}

if (sessionStorage.getItem('userRole') == 'Manager') {

    function approve(val) {

        approveUpdate(val);

        function approveUpdate(val) {
            let userId = sessionStorage.getItem('currentUser');
            let reimbId = val;

            console.log(userId);
            console.log(reimbId);

            fetch('http://localhost:8080/project-1/approveReimb', {
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

                if (data.status == 'process failed') {
                    console.log('process failed')
                    alert('Request failed, please try again!')
                }else if(data.status == 'unauthorized access'){
                    console.log('unauthorized access')
                    window.location.href = 'http://localhost:8080/project-1/resources/html/error.html'
                } else if (data.status == 'Approved') {
                    if (data.email == 'Email Success') {
                        alert("Approved success and email sent successfully");
                        window.location.href = 'http://localhost:8080/project-1/resources/html/MngApproveDeny.html'
                    }
                }

            }).catch(error => {
                console.log(error);
                window.location.href = 'http://localhost:8080/project-1/resources/html/error.html'
            })

        }
    }


    function deny(val) {

        denyUpdate(val);

        function denyUpdate(val) {
            let userId = sessionStorage.getItem('currentUser');
            let reimbId = val;

            console.log(userId);
            console.log(reimbId);

            fetch('http://localhost:8080/project-1/denyReimb', {
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

                if (data.status == 'process failed') {
                    console.log("request failed");
                    alert('Request failed, please try again!')
                }else if(data.status == 'unauthorized access'){
                    console.log('unauthorized access')
                    window.location.href = 'http://localhost:8080/project-1/resources/html/error.html'
                } else if (data.status == 'Denied') {
                    if (data.email == 'Email Success') {
                        alert("Denied success and email sent successfully");
                        window.location.href = 'http://localhost:8080/project-1/resources/html/MngApproveDeny.html'
                    }
                }


            }).catch(error => {
                console.log(error);
                window.location.href = 'http://localhost:8080/project-1/resources/html/error.html'
            })

        }


    }

    function redirectShowReceipt(val){

        sessionStorage.setItem('receiptId', val);
        window.location.href = 'http://localhost:8080/project-1/resources/html/MngReceipt.html';
         
    }

} else {
    window.location.href = 'http://localhost:8080/project-1/resources/html/error.html'
}
