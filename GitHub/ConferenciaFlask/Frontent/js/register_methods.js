

function Register() {
    let name = document.getElementById('input_name').value
    let user = document.getElementById('input_user').value
    let password = document.getElementById('input_password').value
    let password_repeat = document.getElementById('input_password_2').value


    if (password == password_repeat) {
        var url = 'http://localhost:4000/create-user';
        var data = { "user": user, "name": name, "password": password };

        fetch(url, {
            method: 'POST',
            body: JSON.stringify(data),
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(res => res.json())
            .then(function (response) {
                $('#toast-success').toast('show');
                window.location.href="login.html"
            })
            .catch(function (error) {
                console.log(error)
            })

    } else {
        $('#toast-danger').toast('show');
    }


}