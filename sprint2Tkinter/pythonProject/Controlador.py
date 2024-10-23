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
# Importes
import tkinter as tk

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
'''