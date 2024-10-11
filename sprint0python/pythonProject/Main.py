from Heroe import Heroe
from Mazmorra import Mazmorra
from Monstruo import Monstruo
from Tesoro import Tesoro


def main():
    nombre_heroe = input("Introduce el nombre de tu héroe: ")
    heroe = Heroe(nombre_heroe)
    monstruo = Monstruo("Voldemort", 30, 20, 140)
    tesoro = Tesoro("Beneficio")

    mazmorra = Mazmorra(heroe, monstruo, tesoro)
    mazmorra.jugar()

if __name__ == "__main__":
    main()