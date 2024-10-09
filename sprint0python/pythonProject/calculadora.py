from operaciones import suma, resta, multiplicacion, division

#Defino la función principal
def main():
    continuar = 's'

    #Hago el bucle mientras la respuesta sea s o S
    while continuar == 's' or continuar == 'S':

    #Solicito 2 números al usuario
        n1 = float(input("Introduce el primer número: "))
        n2 = float(input("Introduce el segundo número: "))

    #Solicito al usuario el tipo de operación que desea hacer
        print("Selecciona la operación a realizar:")
        print("1. Suma")
        print("2. Resta")
        print("3. Multiplicación")
        print("4. División")

        operacion = input("Introduce el número de la operación (1/2/3/4): ")

    #Realizo la operación seleccionada
        if operacion == '1':
            resultado = suma(n1, n2)
        elif operacion == '2':
            resultado = resta(n1, n2)
        elif operacion == '3':
            resultado = multiplicacion(n1, n2)
        elif operacion == '4':
            resultado = division(n1, n2)
        else:
            print("Operación no válida.")
            continue

        print("El resultado es:", resultado )

    #Preguntar al usuario si desea realizar otra operación
        continuar = input("¿Quieres realizar otra operación? (s/n): ").lower()
        if continuar != 's' and continuar!= 'S':
            print("FIN DEL PROGRAMA")


#Ejecuto la función principal
if __name__ == "__main__":
    main()
