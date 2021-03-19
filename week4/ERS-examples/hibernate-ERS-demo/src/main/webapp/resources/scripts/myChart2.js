if (sessionStorage.getItem('currentUser') == null) {
    window.location.href = 'http://localhost:8080/project-1/resources/html/error.html'
} else if (sessionStorage.getItem('userRole') == null) {
    window.location.href = 'http://localhost:8080/project-1/resources/html/error.html'
}


const dataYCost = [0,0,0];

chartIt2();

async function chartIt2() {

    await getData();

    const ctx = document.getElementById('chart2').getContext('2d');
    const myChart = new Chart(ctx, {
        type: 'doughnut',
        data: {
            labels: ['Pending', 'Approved', 'Denied'],
            datasets: [{
                label: 'Reimbursement Report',
                data: dataYCost,
                backgroundColor: [
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(255, 99, 132, 0.2)'
                ],
                borderColor: [
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(255, 99, 132, 1)'
                ],
                borderWidth: 1
            }]
        },
        options: {
            title:{
                text: "Reimbursement Costs($)",
                display: true
            },
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        }
    });

}


async function getData() {

    await fetch('http://localhost:8080/project-1/home', {
        method: 'POST',
        redirect: 'follow',
        credentials: 'include',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            userId: sessionStorage.getItem('currentUser')
        })
    }).then(function (response) {

        if (!response.ok) {
            throw Error("ERROR");
        }

        return response.json();
    }).then(function (data) {
        console.log(data)

        data.forEach(obj => {

            if (obj.reimbStatusId == '1') {
                dataYCost[0] = dataYCost[0] + obj.reimbAmount;
            } else if (obj.reimbStatusId == "2") {
                dataYCost[1] = dataYCost[1] + obj.reimbAmount;
            } else if (obj.reimbStatusId == "3") {
                dataYCost[2] = dataYCost[2] + obj.reimbAmount;
            }

        });

        console.log("c1: " + dataYValues[0])
        console.log("c2: " + dataYValues[1])
        console.log("c3: " + dataYValues[2])

        
    }).catch(error => {
        console.log(error);
      //  window.location.href = 'http://localhost:8080/project-1/resources/html/error.html'
    });
}




