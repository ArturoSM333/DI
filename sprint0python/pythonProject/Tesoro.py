import random
from Heroe import *


class Tesoro:
    def __init__(self,beneficio):

    #ATRIBUTOS

        self.beneficio = beneficio


    #-------------------------------------MÉTODOS----------------------------------------

    #---MÉTODO--DE--TESORO---
    # Este método le suma al héroe los puntos (ataque, defensa o salud) que se elijan al azar.



    def encontrar_tesoro(self, heroe):
        numero_aleatorio = random.randint(1, 3)
        beneficio = self.tipo_de_beneficio(numero_aleatorio)
        print("Héroe ha encontrado un tesoro: ", beneficio,".")
        if numero_aleatorio == 1:
            heroe.ataque += 5
            print("El ataque de ", heroe.nombre, " aumenta a ", heroe.ataque,".")
        else:
            if numero_aleatorio == 2:
                heroe.defensa += 4
                print("La defensa de ", heroe.nombre, " aumenta a ", heroe.defensa,".")
            else:
                heroe.salud = heroe.salud_maxima
                print("La salud de ", heroe.nombre," ha sido restaurada a ", heroe.salud,".")



    # ---MÉTODO--DE--AZAR---
    # Este método según el número elegido al azar anteriormente elige uno de los 3 posibles beneficios.


    def tipo_de_beneficio(self, numero_aleatorio):
        switch = {
            1: 'Aumento de ataque',
            2: 'Aumento de defensa',
            3: 'Restauración de salud'
        }
        return switch.get(numero_aleatorio)

