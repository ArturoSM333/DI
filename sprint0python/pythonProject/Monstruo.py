#ATRIBUTOS DE OTRAS CLASES
from Heroe import *


class Monstruo:
    def __init__(self, nombre, ataque, defensa, salud):

    #ATRIBUTOS

        self.nombre = nombre
        self.ataque = ataque
        self.defensa = defensa
        self.salud = salud

#-------------------------------------MÉTODOS----------------------------------------

#---MÉTODO--DE--ATAQUE---
# Este método resta la defensa del héroe al ataque del enemigo.
# La cantidad de puntos de ataque que sobrepasen la defensa del héroe será la cantidad de daño que le haga.
# Si la defensa sobrepasa al ataque, no le haremos daño al héroe.
# dano=daño

    def atacar(self, heroe):
        print("El monstruo"+self.nombre+" ataca a ", heroe.nombre)
        if self.ataque > heroe.defensa:
            dano = self.ataque - heroe.defensa
            heroe.salud -= dano
            print("El héroe ", heroe.nombre, " ha recibido ", dano, "puntos de daño.")
        else: print("El héroe ha bloqueado el ataque.")


#---MÉTODO--DE--COMPROBACIÓN--DE--VIDA---

#Este método comprueba si el enemigo sigue con vida.

    def esta_vivo(self):
        if self.salud <= 0:
            return False
        else:
            return True
