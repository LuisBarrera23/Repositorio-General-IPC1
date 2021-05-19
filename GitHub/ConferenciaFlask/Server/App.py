import json

from flask import Flask, request, jsonify
from flask_cors import CORS, cross_origin



app = Flask(__name__)
cors = CORS(app)
app.config['CORS_HEADERS'] = 'Content-Type'


movies=[]
usuarios=[]

# routes

@app.route('/', methods=['GET','POST'])
def main_function():
    if request.method == 'GET':
        return "Conferencia Python Flask"    
    elif request.method == 'POST':
        params=json.loads(request.data)
        print(params)
        cadena="Servidor creado con "+params['tema']+" el cual utiliza el lenguaje "+params['lenguaje']
        return cadena


@app.route('/login', methods=['POST'])
def login():
    if request.method == 'POST':
        params=json.loads(request.data)

        user_p=params['user']
        password_p=params['password']

        for usuario in usuarios:
            if user_p == usuario['user']:
                if password_p == usuario['password']:
                    return jsonify({"status": 200, "mensaje": "Credenciales correctas","data":True,"user":usuario})
                else:
                    return jsonify({"status": 200, "mensaje": "Credenciales incorrectas","data":False})

        return jsonify({"status": 200, "mensaje": "Credenciales incorrectas", "data":False})



@app.route('/create-user', methods=['POST'])
def create_user():
    if request.method == 'POST':
        params=json.loads(request.data)
        
        new_id=len(usuarios)+1

        new_user = {
            'id':new_id,
            'user':params['user'],
            'name':params['name'],
            'password':params['password']
        }

        usuarios.append(new_user)
        return jsonify({"status": 200, "mensaje": "Usuario creado correctamente"})



@app.route('/create-movie', methods=['POST'])
def create_movie():
    if request.method == 'POST':
        params=json.loads(request.data)
        
        new_id=len(movies)+1
        new_movie = {
            'id':new_id,
            'title':params['title'],
            'director':params['director'],
            'year':params['year'],
            'rating':params['rating']
        }
        movies.append(new_movie)

        return jsonify({"status": 200, "mensaje": "Pelicula creada correctamente"})


@app.route('/get-movies', methods=['GET'])
def get_movies():
    if request.method == 'GET':
        return jsonify({"status": 200, "data":movies})



# starting the app
if __name__ == "__main__":
    app.run(port=4000, debug=True)
