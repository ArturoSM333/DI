#SUMA, RESTA, MULTIPLICACIÓN, DIVISIÓN


#FUNCIÓN SUMA
def suma (n1,n2):
    return n1 + n2

#FUNCIÓN RESTA
def resta (n1,n2):
    return n1 - n2

#FUNCIÓN MULTIPLICACIÓN
def multiplicacion (n1,n2):
    return n1 * n2

#FUNCIÓN DIVISIÓN
#Controlo la división por 0
def division (n1, n2):
    if n2 == 0:
        return "No se puede dividir por 0"
        return n1 / n2

print("El resultado de la suma es: ", suma(2,4))
print("El resultado de la resta es: ", resta(8,3))
print("El resultado de la multiplicación es: ", multiplicacion(3,4))
print("El resultado de la división es: ", division(2,0))




