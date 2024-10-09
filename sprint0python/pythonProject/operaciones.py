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
    try:
        return n1 / n2
    except ZeroDivisionError:
        print("No se puede dividir por cero")

print("El resultado de la suma es: ", suma(2,4))
print("El resultado de la resta es: ", resta(8,3))
print("El resultado de la multiplicación es: ", multiplicacion(3,4))
print("El resultado de la división es: ", division(2,0))




