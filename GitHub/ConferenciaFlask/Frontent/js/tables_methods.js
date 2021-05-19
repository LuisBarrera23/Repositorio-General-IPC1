
function get_movies() {

    get_data();

    var url = 'http://localhost:4000/get-movies';

    fetch(url, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(res => res.json())
        .then(function (response) {
            var array = response.data;
            console.log(array)

            var new_html = ""

            for (let i = 0; i < array.length; i++) {
                new_html += `\n<tr>
                <td>`+ array[i].title + `</td>
                <td>`+ array[i].director + `</td>
                <td>`+ array[i].year + `</td>
                <td>`+ array[i].rating + `</td>
            </tr>`

            }

            $('#body_table').append(new_html);

            get_ratings(array);

        })
        .catch(function (error) {
            console.log(error)
        })



}


function get_ratings(array) {
    let cantidad = document.getElementById('cantidad_peliculas')
    let max = document.getElementById('max_rating')
    let min = document.getElementById('min_rating')

    cantidad.innerHTML = array.length

    let max_rating = 0, min_rating = 0, max_position = 0, min_position = 0;


    for (let i = 0; i < array.length; i++) {
        var current_rating = parseInt(array[i].rating);
        if (current_rating > max_rating) {
            max_rating = current_rating;
            max_position = i;
        }
    }

    for (let i = 0; i < array.length; i++) {
        var current_rating = parseInt(array[i].rating);
        if (current_rating < max_rating) {
            min_rating = current_rating;
            min_position = i;
        }
    }

    max.innerHTML = array[max_position].title;
    min.innerHTML = array[min_position].title;


}