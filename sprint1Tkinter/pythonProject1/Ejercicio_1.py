'''
Ejercicio 1: Label
Crea una ventana que muestre tres etiquetas (Label). La primera debe mostrar un
mensaje de bienvenida, la segunda debe mostrar tu nombre, y la tercera debe cambiar su
texto al hacer clic en un botón.
'''

import tkinter as tk

# Crear la ventana principal

root = tk.Tk()
root.title("Ejemplo de Label")
root.geometry("300x150")


# Crear etiquetas con diferentes propiedades

etiqueta_1 = tk.Label(root, text="Bienvenido a label")
etiqueta_1.pack(pady=5)
etiqueta_2 = tk.Label(root, text="Arturo Suárez Martín")
etiqueta_2.pack(pady=5)
etiqueta_3 = tk.Label(root, text="Etiqueta 3")
etiqueta_3.pack(pady=5)


# Creo el botón cambia-texto

def nuevo_texto():
    etiqueta_3.config(text= "Modificación de etiqueta 3")

boton = tk.Button(root, text = "Haz click para modificar el texto", command=nuevo_texto)
boton.pack(pady= 5)


# Ejecutamos el bucle principal

root.mainloop()