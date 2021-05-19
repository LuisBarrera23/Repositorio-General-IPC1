from flask import Flask,jsonify,request

from flask_cors import CORS

import json

app=Flask(__name__)

CORS(app)
def verificar(caracter):
        return caracter in 'aeiou'

@app.route('/analizar', methods=['POST'])
def analizarfrase():
    cadena=request.json['frase']
    print(cadena)
    vocales=0
    for frase in cadena:
        if frase.lower() in "aeiou":
            vocales+=1

    print("la frase tiene ",vocales, " vocales")

    separacion=cadena.split()
    palabras=0
    for i in separacion:
        print(i)
        palabras+=1

    print("la frase tiene ",palabras," palabras")

    

    minusculas=cadena.lower()
    consonantes=0
    for caracter in minusculas:
        if caracter.isalpha() and not verificar(caracter):
            consonantes+=1

    print('hay ',consonantes,' consonantes') 
    
    objeto={
        'palabras':palabras,
        'vocales':vocales,
        'consonantes':consonantes
    }
    return (jsonify(objeto))  






if __name__=="__main__":
    app.run(host="0.0.0.0",port=3000,debug=True)

