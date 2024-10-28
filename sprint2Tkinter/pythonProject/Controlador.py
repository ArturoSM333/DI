'''

Controlador (Manejo de lógica de interacción):

Crearás una clase ControladorNotas que conectará la vista con el modelo y manejará la interacción del usuario:
Método agregar_nota(): Obtiene el texto del Entry de la vista y llama al modelo para agregar la nota, luego actualiza la vista.
Método eliminar_nota(): Elimina la nota seleccionada en el Listbox y actualiza la vista.
Método guardar_notas(): Guarda las notas en el archivo de texto llamando al modelo y muestra un mensaje de confirmación.
Método cargar_notas(): Carga las notas desde el archivo de texto y actualiza la vista.
Método descargar_imagen(): Descarga la imagen desde GitHub en un hilo separado para no congelar la interfaz gráfica.
Método actualizar_coordenadas(event): Captura las coordenadas del clic del ratón fuera de los botones y actualiza un Label en la vista.

'''

'''
3. Controlador: ControladorNotas
Clase ControladorNotas:

Esta clase gestionará la lógica de la interacción entre el usuario, la vista y el modelo.
Métodos del controlador:

agregar_nota():
Obtiene el texto del widget Entry de la vista utilizando self.vista.entry_nota.get().
Llama al método agregar_nota() del modelo para agregar la nueva nota.
Actualiza el Listbox llamando a actualizar_listbox().
eliminar_nota():
Obtiene el índice seleccionado del Listbox usando self.vista.listbox.curselection().
Llama al método eliminar_nota() del modelo para eliminar la nota en el índice seleccionado.
Actualiza el Listbox llamando a actualizar_listbox().
guardar_notas():
Llama al método guardar_notas() del modelo para guardar la lista de notas en un archivo.
Muestra un mensaje de confirmación con messagebox.showinfo().
cargar_notas():
Llama al método cargar_notas() del modelo para cargar las notas desde un archivo.
Actualiza el Listbox llamando a actualizar_listbox().
descargar_imagen():
Llama a un método que descarga una imagen desde GitHub utilizando la biblioteca requests. La descarga se realiza en un hilo separado usando threading.Thread().
Usa after() para actualizar la interfaz gráfica con la imagen descargada.
actualizar_coordenadas(event):
Captura las coordenadas del clic y actualiza el Label de coordenadas usando event.x y event.y.
actualizar_listbox():
Eliminar los elementos actuales del Listbox usando delete(0, tk.END).
Obtener las notas actuales desde el modelo llamando a obtener_notas().
Insertar cada nota en el ´Listbox´ usando insert(tk.END, nota) en un bucle para asegurarse de que todas las notas se reflejan en la interfaz gráfica.
'''

from tkinter import messagebox
import tkinter as tk
from PIL import Image, ImageTk
import requests
from io import BytesIO
import threading


class ControladorNotas:
    """
    Controlador para gestionar la lógica de la aplicación de notas, incluyendo
    operaciones de agregar, eliminar, guardar y cargar notas, así como
    manejar la descarga y visualización de imágenes.
    """

    def __init__(self, modelo, vista):
        self.modelo = modelo
        self.vista = vista

        # Asignación de funciones a los botones de la vista
        self.vista.button_agregar.config(command=self.agregar_nota)
        self.vista.button_eliminar.config(command=self.eliminar_nota)
        self.vista.button_guardar.config(command=self.guardar_notas)
        self.vista.button_cargar.config(command=self.cargar_notas)
        self.vista.root.bind("<Button-1>", self.actualizar_coordenadas)
        self.vista.button_descargar.config(command=self.iniciar_descarga)

    def actualizar_listbox(self):
        self.vista.listbox.delete(0, tk.END)
        for nota in self.modelo.obtener_notas():
            self.vista.listbox.insert(tk.END, nota)

    def agregar_nota(self):
        nota = self.vista.entrada.get()
        self.modelo.agregar_nota(nota)
        self.actualizar_listbox()

    def eliminar_nota(self):
        seleccion = self.vista.listbox.curselection()
        if seleccion:
            indice = seleccion[0]
            self.modelo.eliminar_nota(indice)
        else:
            messagebox.showwarning("Advertencia", "Por favor selecciona una nota para eliminar.")
        self.actualizar_listbox()

    def guardar_notas(self):
        self.modelo.guardar_notas()
        messagebox.showinfo("Información", "Notas descargadas")

    def cargar_notas(self):
        self.vista.listbox.delete(0, tk.END)
        for nota in self.modelo.cargar_notas():
            self.vista.listbox.insert(tk.END, nota)

    def actualizar_coordenadas(self, event):
        self.vista.etiqueta_coordenadas.config(text=f"Coordenadas: {event.x} , {event.y}")

    def descargar_imagen(self, url, callback):
        try:
            respuesta = requests.get(url)
            respuesta.raise_for_status()  # Lanza excepción si falla la descarga
            imagen = Image.open(BytesIO(respuesta.content)).resize((150, 150), Image.Resampling.LANCZOS)
            imagen_tk = ImageTk.PhotoImage(imagen)
            self.vista.root.after(0, callback, imagen_tk)  # Llama al callback en el hilo principal
        except requests.exceptions.RequestException as e:
            print(f"Error al descargar la imagen: {e}")
            self.vista.root.after(0, callback, None)  # Maneja error en el hilo principal

    def actualizar_etiqueta(self, imagen_tk):
        if imagen_tk:
            self.vista.etiqueta_imagen.config(image=imagen_tk)
            self.vista.etiqueta_imagen.image = imagen_tk  # Mantiene referencia de la imagen
            self.redimensionar_pantalla()  # Ajusta tamaño de la ventana
            self.vista.root.after(50, self.centrar_pantalla)  # Centra ventana tras ajuste
        else:
            self.vista.etiqueta_imagen.config(text="Error al descargar la imagen.")

    def iniciar_descarga(self):
        url = ''
        hilo = threading.Thread(target=self.descargar_imagen, args=(url, self.actualizar_etiqueta))
        hilo.start()

    def centrar_pantalla(self):
        ancho_pantalla = self.vista.root.winfo_screenwidth()
        alto_pantalla = self.vista.root.winfo_screenheight()
        ancho_ventana = self.vista.root.winfo_width()
        alto_ventana = self.vista.root.winfo_height()
        pwidth = round(ancho_pantalla / 2 - ancho_ventana / 2)
        pheight = round(alto_pantalla / 2 - alto_ventana / 2)
        posicion = f"{ancho_ventana}x{alto_ventana}+{pwidth}+{pheight}"
        self.vista.root.geometry(posicion)

    def redimensionar_pantalla(self):
        nuevo_alto = self.vista.root.winfo_height() + self.vista.etiqueta_imagen.winfo_reqheight()
        self.vista.root.geometry(f"600x{nuevo_alto}")