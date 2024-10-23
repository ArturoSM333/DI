'''

Modelo (Manejo de datos):

Crearás una clase NotasModel que gestionará la lógica de las notas:
Método agregar_nota(nueva_nota): Añade una nueva nota a la lista de notas.
Método eliminar_nota(indice): Elimina la nota en el índice especificado.
Método obtener_notas(): Devuelve la lista de notas.
Método guardar_notas(): Guarda las notas en un archivo de texto (notas.txt).
Método cargar_notas(): Carga las notas desde el archivo de texto (notas.txt) al iniciar la aplicación.

'''
# Importes
import tkinter as tk

'''
1. Modelo: NotasModel
Clase NotasModel:

Esta clase gestionará la lista de notas y proporcionará métodos para agregar, eliminar y recuperar las notas.
Atributo:
self.notas: Lista que almacena las notas.

Métodos del modelo:

agregar_nota(nueva_nota): Añade una nueva nota a la lista de notas. Utiliza el método append() para agregar la nueva nota.
eliminar_nota(indice): Elimina una nota en un índice específico. Usa del self.notas[indice] para eliminarla.
obtener_notas(): Devuelve la lista completa de notas. Este método retorna el valor de self.notas.
guardar_notas(): Abre el archivo notas.txt en modo escritura ('w'), y escribe cada nota en una nueva línea utilizando write().
cargar_notas(): Abre el archivo notas.txt en modo lectura ('r') y lee cada línea, eliminando los saltos de línea usando strip().
'''

class NotasModel:

    def __init__(self):
        self.notas = notas


    def agregar_nota(self, nueva_nota):



    def eliminar_nota(self):


    def obtener_notas(self):


    def guardar_notas(self):


    def cargar_notas(self):

