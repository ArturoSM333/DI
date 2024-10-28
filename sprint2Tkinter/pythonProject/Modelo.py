'''

Modelo (Manejo de datos):

Crearás una clase NotasModel que gestionará la lógica de las notas:
Método agregar_nota(nueva_nota): Añade una nueva nota a la lista de notas.
Método eliminar_nota(indice): Elimina la nota en el índice especificado.
Método obtener_notas(): Devuelve la lista de notas.
Método guardar_notas(): Guarda las notas en un archivo de texto (notas.txt).
Método cargar_notas(): Carga las notas desde el archivo de texto (notas.txt) al iniciar la aplicación.

'''
from importlib.util import source_hash

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
        self.notas = []

    def agregar_nota(self, nueva_nota):
        self.notas.append(nueva_nota)

    def eliminar_nota(self, indice):
        self.notas.pop(indice)

    def obtener_notas(self):
        return self.notas

    def guardar_notas(self):
        with open("Txt_e_imagen/nota.txt", "w") as archivo:
            for nota in self.notas:
                archivo.write(nota + "\n")

    def cargar_notas(self):
        notas_txt = []
        with open("Txt_e_imagen/nota.txt", "r") as archivo:
            for linea in archivo:
                notas_txt.append(
                    linea.strip())  # Elimina espacios y saltos de línea
        return notas_txt