
function create_movie() {

    let title = document.getElementById('input_title').value
    let director = document.getElementById('input_director').value
    let year = document.getElementById('input_year').value
    let rating = document.getElementById('input_rating').value

    var url = 'http://localhost:4000/create-movie';

    var data = { "title": title, "director": director, "year": year, "rating": rating };

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
            location.reload();
        })
        .catch(function (error) {
            console.log(error)
        })



}




