from Heroe import *
from Monstruo import *
from Tesoro import *

class Mazmorra:
    def __init__(self, heroe, monstruo, tesoro):
        self.heroe = heroe
        self.monstruo = monstruo
        self.tesoro = tesoro



    #-------------------------------------MÉTODOS----------------------------------------

    #---MÉTODO--DE--JUGAR---
    # Este método te hace empezar la partida.

    def jugar(self):
        print("Héroe entra a la mazmorra.")
        print("Te has encontrado con un ", self.monstruo.nombre, ".")
        while self.heroe.esta_vivo() and self.monstruo.esta_vivo():
            self.enfrentar_enemigo(self.monstruo)
            if self.monstruo.esta_vivo():
                self.monstruo.atacar(self.heroe)

        if not self.heroe.esta_vivo():
            print("Héroe ha salido derrotado en la mazmorra.")

        if not self.monstruo.esta_vivo():
            self.buscar_tesoro()
            print(self.heroe.nombre, " ha derrotado a todos los monstruos y ha conquistado la mazmorra!")



    #---MÉTODO--DE--ENFRENTAMIENTO---
    # Este método pregunta al jugador su próximo movimiento.

    def enfrentar_enemigo(self, enemigo):
        print("¿Qué deseas hacer?")
        print("1. Atacar")
        print("2. Defender")
        print("3. Curarse")
        movimiento = int(input())
        switch = {
            1: lambda: self.heroe.atacar(self.monstruo),
            2: lambda: self.heroe.defenderse(),
            3: lambda: self.heroe.curarse()
        }
        accion = switch.get(movimiento)
        if accion:
            accion()
        else:
            print("Opción no válida. Intenta de nuevo.")

    #---MÉTODO--DE--BUSCAR--EL--TESORO---
    # Este método se ejecuta al acabar con el monstruo, para buscar el tesoro correspondiente.
    def buscar_tesoro(self):
        print("Buscando tesoro...")
        self.tesoro.encontrar_tesoro(self.heroe)