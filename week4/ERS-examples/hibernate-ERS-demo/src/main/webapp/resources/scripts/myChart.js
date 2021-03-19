if (sessionStorage.getItem('currentUser') == null) {
    window.location.href = 'http://localhost:8080/project-1/resources/html/error.html'
} else if (sessionStorage.getItem('userRole') == null) {
    window.location.href = 'http://localhost:8080/project-1/resources/html/error.html'
}

chartIt();

const dataYValues = [0,0,0,0];

async function chartIt() {

    await getData();

    const ctx = document.getElementById('chart1').getContext('2d');
    const myChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: ['Pending', 'Approved', 'Denied', 'Total'],
            datasets: [{
                 label: 'Reimbursement Report',
                data: dataYValues,
                backgroundColor: [
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)'
                ],
                borderColor: [
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                ],
                borderWidth: 1
            }]
        },
        options: {
            title:{
                text: "Reimbursement Status(#)",
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
                dataYValues[0]++;

            } else if (obj.reimbStatusId == "2") {
                dataYValues[1]++

            } else if (obj.reimbStatusId == "3") {
                dataYValues[2]++
            }

        });

        dataYValues[3] = dataYValues[0] + dataYValues[1] + dataYValues[2];
        console.log("val1: " + dataYValues[0])
        console.log("val2: " + dataYValues[1])
        console.log("val3: " + dataYValues[2])
        console.log("val4: " + dataYValues[3])

        
    }).catch(error => {
        console.log(error);
      //  window.location.href = 'http://localhost:8080/project-1/resources/html/error.html'
    });
}




