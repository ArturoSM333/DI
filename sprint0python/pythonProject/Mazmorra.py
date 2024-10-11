from Heroe import *
from Monstruo import *
from Tesoro import *

class Mazmorra:
    def __init__(self, heroe, monstruo, tesoro):
        self.heroe = heroe
        self.monstruo = monstruo
        self.tesoro = tesoro




    def jugar(self):
        print("Héroe entra a la mazmorra.")
        print("Te has encontrado con un ", self.monstruo.nombre, ".")
        while self.heroe.esta_vivo()!=0 and self.monstruo.esta_vivo()!=0:
            self.enfrentar_enemigo(self.monstruo)

        if self.heroe.esta_vivo()==0:
            print("Héroe ha salido derrotado en la mazmorra.")

        if self.monstruo.esta_vivo() == 0:
            self.buscar_tesoro()
            print(self.heroe.nombre, " ha derrotado a todos los monstruos y ha conquistado la mazmorra!")



    def enfrentar_enemigo(self, enemigo):
        print("¿Qué deseas hacer?")
        print("1. Atacar")
        print("2. Defender")
        print("3. Curarse")
        movimiento = input()
        switch = {
            1: self.heroe.atacar(self.monstruo),
            2: self.heroe.defenderse(),
            3: self.heroe.curarse()
        }
        return switch.get(movimiento)


    def buscar_tesoro(self):
        print("Buscando tesoro...")
        self.tesoro.encontrar_tesoro()