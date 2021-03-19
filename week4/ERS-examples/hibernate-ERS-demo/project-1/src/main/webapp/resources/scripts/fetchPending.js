if (sessionStorage.getItem('currentUser') == null) {
    window.location.href = 'http://localhost:8080/project-1/resources/html/error.html'
} else if (sessionStorage.getItem('userRole') == null) {
    window.location.href = 'http://localhost:8080/project-1/resources/html/error.html'
}

if (sessionStorage.getItem('userRole') == 'Manager') {

    fetchData();

    function fetchData() {

        fetch('http://localhost:8080/project-1/getReimbsByStatus', {
            method: 'POST',
            redirect: 'follow',
            credentials: 'include',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                userId: sessionStorage.getItem('currentUser'),
                status: 'Pending'
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
            } else if(data.status == 'unauthorized access'){
                console.log('unauthorized access')
                window.location.href = 'http://localhost:8080/project-1/resources/html/error.html'
            } else if(data.status == 'no record') {
                console.log('no record')
                alert('There is no reimbursement in the system')
            } else {

                const html = data.map(reimb => {
                    return `
                        <tr>
                        <td>${reimb.reimbId}</td>
                        <td>${reimb.reimbAuthor}</td>
                        <td><b>${reimb.reimbAmount}</b></td>
                        <td>${reimb.reimbType}</td>
                        <td><span class="label label-warning">${reimb.reimbStatus}</td>
                        <td>${reimb.reimbSubmitted}</td>
                        <td>${reimb.reimbDescription}</td>
                        <td><button type="submit" class="btn btn-primary " id="showImg" onclick="redirectShowReceipt(${reimb.reimbId})" value=${reimb.reimbId}>Receipt</button></td>
                        <td><button type="submit" class="btn btn-primary btnApprove" id="approve" onclick="approve(${reimb.reimbId})" value=${reimb.reimbId}>Approve</button></td> 
                        <td><button type="submit" class="btn btn-primary btnDeny" id="deny" onclick="deny(${reimb.reimbId})" value=${reimb.reimbId}>Deny</button></td>
                        </tr>
                `
                })
                .join("");

                console.log(html);
                document.querySelector('#app').insertAdjacentHTML('beforeend', html);
            }


        }).catch(error => {
            console.log(error);
            window.location.href = 'http://localhost:8080/project-1/resources/html/error.html'
        });

    }


} else {
    window.location.href = 'http://localhost:8080/project-1/resources/html/error.html'
}


function redirectShowReceipt(val){

    sessionStorage.setItem('receiptId', val);
    window.location.href = 'http://localhost:8080/project-1/resources/html/showReceipt.html';
     
}
