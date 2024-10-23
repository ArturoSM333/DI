'''

Vista (Interfaz gráfica):

Utilizarás diferentes widgets de Tkinter para crear la interfaz gráfica:
Label: Para mostrar el título de la aplicación y las coordenadas donde se haga clic.
Listbox: Para mostrar las notas que el usuario añade.
Entry: Para que el usuario escriba una nueva nota.
Button: Para agregar notas, eliminar notas, guardar notas en un archivo, cargar notas desde un archivo y descargar una imagen desde GitHub.
Label: Para mostrar la imagen descargada desde GitHub.

'''
# Importes
import tkinter as tk
from imghdr import tests

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

class VistaNotas:

    # Ventana principal
    root = tk.Tk()
    root.title("Ejercicio de práctica de conceptos")
    root.eval('tk::PlaceWindow . center')

    # Etiqueta para el título de la aplicación y las coordenadas donde se haga clic
    etiqueta_titulo = tk.Label (root, text="Aplicación de gestión de notas")
    etiqueta_titulo.pack(pady= 10)

    etiqueta_coordenadas = tk.Label (root, text="Coordenadas del ratón: ")
    etiqueta_coordenadas.pack(pady=5)

    # Lista de notas
    notas=tk.Listbox(root)
    notas.pack(pady=5)

    # Entrada de notas
    entrada_nota = tk.Entry(root)
    entrada_nota.pack(pady=5)

    # Botones para agregar notas, eliminar notas, guardar notas en un archivo, cargar notas desde un archivo y descargar una imagen desde GitHub.
    boton_agregar = tk.Button(root, text="Agregar nota")
    boton_agregar.pack(pady=5)

    boton_eliminar = tk.Button(root, text="Eliminar nota")
    boton_eliminar.pack(pady=5)

    boton_guardar = tk.Button(root, text="Guardar notas")
    boton_guardar.pack(pady=5)

    boton_cargar = tk.Button(root, text="Cargar notas")
    boton_cargar.pack(pady=5)

    boton_descargar = tk.Button(root, text="Descargar imagen")
    boton_descargar.pack(pady=5)

    # Ejecutamos bucle principal
    root.mainloop()