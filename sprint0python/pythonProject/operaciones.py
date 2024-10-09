suma = 3+5
resta = 9-4
multiplicacion = 5*2
division = 13/2

print("El resultado de la suma es: ", suma)
print("El resultado de la resta es: ", resta)
print("El resultado de la multiplicación es: ", multiplicacion)

try:
    print("El resultado de la división es: ", division)
except ZeroDivisionError:
    print("No se puede dividir por cero")

