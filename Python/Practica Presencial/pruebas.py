cadena="aca ira la frase para analizar"

vocales=0

for frase in cadena:
    if frase.lower() in "aeiou":
        vocales+=1

print("la frase tiene ",vocales, " vocales")

separacion=cadena.split()
palabras=0
for i in separacion:
    palabras+=1

print("la frase tiene ",palabras," palabras")

def verificar(caracter):
    return caracter in 'aeiou'

minusculas=cadena.lower()
consonantes=0
for caracter in minusculas:
    if caracter.isalpha() and not verificar(caracter):
        consonantes+=1

print('hay ',consonantes,' consonantes')


