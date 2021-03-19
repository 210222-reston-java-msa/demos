if (sessionStorage.getItem('currentUser') == null) {
    window.location.href = 'http://localhost:8080/project-1/resources/html/error.html'
} else if (sessionStorage.getItem('userRole') == null) {
    window.location.href = 'http://localhost:8080/project-1/resources/html/error.html'
}

if (sessionStorage.getItem('userRole') == 'Employee') {

    fetchData();

    function fetchData() {

        fetch('http://localhost:8080/project-1/getUser')
            .then(response => {

                if (!response.ok) {
                    throw Error("ERROR");
                }

                return response.json();
            }).then(data => {
                console.log(data);

                if (data.status == 'process failed') {
                    console.log("request failed");
                    alert('Request failed, please try again!')
                } else if (data.status == 'no record') {
                    console.log('no record')
                    alert('There is no reimbursement in the system')
                } else if (data.status == 'unauthorized access') {
                    console.log('unauthorized access')
                    window.location.href = 'http://localhost:8080/project-1/resources/html/error.html'
                } else {

                    let userId = document.getElementById('app1');
                    userId.innerHTML = `${data.userId}`;
                    let username = document.getElementById('app2');
                    username.innerHTML = `${data.username}`;
                    let firstName = document.getElementById('app3');
                    firstName.innerHTML = `${data.firstName}`;
                    let lastName = document.getElementById('app4');
                    lastName.innerHTML = `${data.lastName}`;
                    let email = document.getElementById('app5');
                    email.innerHTML = `${data.email}`;
                    let role = document.getElementById('app6');
                    role.innerHTML = `${data.role.role}`;
                }

            }).catch(error => {
                console.log(error);
                window.location.href = 'http://localhost:8080/project-1/resources/html/error.html'
            });
    }

} else if (sessionStorage.getItem('userRole') == 'Manager') {

    fetchData();

    function fetchData() {

        fetch('http://localhost:8080/project-1/getAllUsers')
            .then(response => {

                console.log("current user= ");
                console.log(sessionStorage.getItem('currentUser'));
                if (!response.ok) {
                    throw Error("ERROR");
                }

                return response.json();
            }).then(data => {
                console.log(data);

                if (data.status == 'process failed') {
                    console.log("request failed");
                    alert('Request failed, please try again!')
                } else if (data.status == 'no record') {
                    console.log('no record')
                    alert('There is no employee in the system')
                } else if (data.status == 'unauthorized access') {
                    console.log('unauthorized access')
                    window.location.href = 'http://localhost:8080/project-1/resources/html/error.html'
                } else {

                    const html = data.map(user => {


                        let labelStatus = 'success';
                        let dataStatus = 'all';
                        if (user.role.role === 'Manager') {
                            labelStatus = 'success';
                            dataStatus = 'inactive';
                        } else if (user.role.role === 'Employee') {
                            labelStatus = 'warning';
                            dataStatus = 'active';
                        }

                        return `
                        <tr data-status="${dataStatus}">
                        <td>${user.userId}</td>
                        <td>${user.username}</td>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>${user.email}</td>
                        <td><span class="label label-${labelStatus}">${user.role.role}</span></td>               
                        </tr>
                    `
                    }).join("");

                    console.log(html);
                    document.querySelector('#app').insertAdjacentHTML('beforeend', html);

                }
            }).catch(error => {
                console.log(error);
                window.location.href = 'http://localhost:8080/project-1/resources/html/error.html'
            });

    }

    $(document).ready(function () {
        $(".btn-group .btn").click(function () {
            var inputValue = $(this).find("input").val();
            if (inputValue != 'all') {
                var target = $('table tr[data-status="' + inputValue + '"]');
                $("table tbody tr").not(target).hide();
                target.fadeIn();
            }
            else {
                $("table tbody tr").fadeIn();
            }
        });
        // Changeing the class of status label to support bootstrap 4
        var bs = $.fn.tooltip.Constructor.VERSION;
        var support = bs.split(".");
        if (str[0] == 4) {
            $(".label").each(function () {
                var classStr = $(this).attr("class");
                var newClassStr = classStr.replace(/label/g, "badge");
                $(this).removeAttr("class").addClass(newClassStr);
            });
        }
    });

} else {
    window.location.href = 'http://localhost:8080/project-1/resources/html/error.html'
}

