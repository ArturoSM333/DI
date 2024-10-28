'''

Vista (Interfaz gráfica):

Utilizarás diferentes widgets de Tkinter para crear la interfaz gráfica:
Label: Para mostrar el título de la aplicación y las coordenadas donde se haga clic.
Listbox: Para mostrar las notas que el usuario añade.
Entry: Para que el usuario escriba una nueva nota.
Button: Para agregar notas, eliminar notas, guardar notas en un archivo, cargar notas desde un archivo y descargar una imagen desde GitHub.
Label: Para mostrar la imagen descargada desde GitHub.

'''

'''
2. Vista: VistaNotas
Clase VistaNotas:

Esta clase gestionará la interfaz gráfica de la aplicación, definiendo los widgets y su disposición.
Widgets utilizados:

Label:
Se utiliza para mostrar el título de la aplicación.
También se usa para mostrar las coordenadas del clic del ratón y la imagen descargada.
Listbox:
Muestra la lista de notas agregadas. Se usará el método insert() para añadir elementos y delete() para eliminarlos.
Entry:
Entrada de texto donde el usuario puede escribir una nueva nota.
Button:
Botones para agregar, eliminar, guardar, cargar notas, y descargar una imagen.
Cada botón estará asociado a una función del controlador mediante la opción command.
Label (para la imagen):
Este Label será donde se mostrará la imagen descargada desde GitHub.
'''

import tkinter as tk

class VistaNotas:

    def __init__(self, root):

        # Configuración de la ventana principal
        self.root = root
        self.root.title("App gestión de notas")
        self.root.geometry("600x450")

        # Etiqueta de título de la aplicación
        self.etiqueta = tk.Label(self.root, text="Aplicación de gestión de notas")
        self.etiqueta.pack(pady=5)

        # Listbox para mostrar las notas
        self.listbox = tk.Listbox(self.root, selectmode=tk.SINGLE, width=50)
        self.listbox.pack(pady=5)

        # Campo de entrada para agregar notas
        self.entrada = tk.Entry(self.root, width=50)
        self.entrada.pack(pady=5)

        # Botón para agregar una nota
        self.button_agregar = tk.Button(self.root, text="Agregar Nota")
        self.button_agregar.pack(pady=5)

        # Botón para eliminar una nota seleccionada
        self.button_eliminar = tk.Button(self.root, text="Eliminar Nota")
        self.button_eliminar.pack(pady=5)

        # Botón para cargar notas desde un archivo
        self.button_cargar = tk.Button(self.root, text="Cargar Notas")
        self.button_cargar.pack(pady=5)

        # Botón para guardar notas en un archivo
        self.button_guardar = tk.Button(self.root, text="Guardar Notas")
        self.button_guardar.pack(pady=5)

        # Botón para descargar y mostrar una imagen
        self.button_descargar = tk.Button(self.root, text="Descargar Imagen")
        self.button_descargar.pack(pady=5)

        # Etiqueta para mostrar las coordenadas del cursor
        self.etiqueta_coordenadas = tk.Label(self.root)
        self.etiqueta_coordenadas.pack(pady=5)

        # Etiqueta para mostrar la imagen descargada
        self.etiqueta_imagen = tk.Label(self.root)
        self.etiqueta_imagen.pack(pady=5)