#ATRIBUTOS DE OTRAS CLASES
from Monstruo import *




class Heroe:
    def __init__(self,nombre):

        #ATRIBUTOS

            self.nombre = nombre
            self.ataque = 31
            self.defensa = 12
            self.salud = 70
            self.salud_maxima = 100


        #-------------------------------------MÉTODOS----------------------------------------

        #---MÉTODO--DE--ATAQUE---
        # Este método resta la defensa del enemigo al ataque del héroe.
        # La cantidad de puntos de ataque que sobrepasen la defensa del enemigo será la cantidad de daño que le hagas.
        # Si la defensa sobrepasa al ataque, no le haremos daño al enemigo.
        # dano=daño

            def atacar(self, monstruo):
                print("Héroe ataca a ", monstruo.nombre)
                if self.ataque > monstruo.defensa:
                    dano = self.ataque - monstruo.defensa
                    print("El enemigo ", monstruo.nombre, " ha recibido ", dano, "puntos de daño.")
                else: print("El enemigo ha bloqueado el ataque.")


        #---MÉTODO--DE--CURARSE---

        # Este método cura al héroe 20 puntos.
        # Si la suma de la cura y la salud sobrepasa la salud máxima el héroe se curará hasta la salud máxima, sin sobrepasarla.

            def curarse(self):
                cura = 20
                if cura+self.salud < self.salud_maxima:
                    self.salud = self.salud + cura
                    print("Héroe se ha curado. Salud actual: ", self.salud, ".")
                else:
                    self.salud = self.salud_maxima
                    print("Héroe se ha curado. Salud actual: ", self.salud, ".")


        #---MÉTODO--DE--DEFENSA---

        # Este método aumenta la defensa del héroe en 5 puntos.

            def defenderse(self):

                self.defensa = self.defensa + 5
                print("Héroe se defiende. Defensa aumentada temporalmente a ", self.defensa, ".")


        #---MÉTODO--DE--RESET--DE--DEFENSA---

        # Este método devuelve los valores iniciales de la defensa del héroe (al acabar el turno de defensa).

            def reset_defensa(self):
                self.defensa = self.defensa - 5
                print("La defensa de ", self.nombre, "ha vuelto a la normalidad.")


        #---MÉTODO--DE--COMPROBACIÓN--DE--VIDA---

        #Este método comprueba si el héroe sigue con vida.

            def esta_vivo(self):
                if self.salud <= 0:
                    return False
                else:
                    return True