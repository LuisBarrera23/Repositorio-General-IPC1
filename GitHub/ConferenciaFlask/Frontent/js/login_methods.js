

function Login() {
    let user = document.getElementById('input_user').value
    let password = document.getElementById('input_password').value




    var url = 'http://localhost:4000/login';
    var data = { "user": user, "password": password };

    fetch(url, {
        method: 'POST',
        body: JSON.stringify(data),
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(res => res.json())
        .then(function (response) {
            console.log(response)
            if (response.data == true) {
                console.log("Credenciales Correctas");
                $('#toast-success').toast('show');
                
                localStorage.setItem('current',JSON.stringify(response.user))

                window.location.href="tables.html"
            } else {
                console.log("Credenciales Incorrectas");
                $('#toast-danger').toast('show');
            }
        })
        .catch(function (error) {
            console.log(error)
        })


}