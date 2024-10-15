'''
Ejercicio 5: Radiobutton
Crea un grupo de tres botones de opción (Radiobutton) para que el usuario elija su color
favorito (rojo, verde o azul). Al seleccionar una opción, cambia el color de fondo de la
ventana al color seleccionado.
'''

import tkinter as tk

# Crear la ventana principal

root = tk.Tk()
root.title("Elige tu color favorito")
root.geometry("300x200")


# Variable para almacenar la opción seleccionada

color_seleccionado = tk.StringVar()


# Función para cambiar el color de fondo según la opción seleccionada

def cambiar_color():
    color = color_seleccionado.get()
    root.config(bg=color)


# Crear los radiobuttons para las opciones de color

radio_rojo = tk.Radiobutton(root, text="Rojo", variable=color_seleccionado, value="red", command=cambiar_color)
radio_rojo.pack(pady=5)

radio_verde = tk.Radiobutton(root, text="Verde", variable=color_seleccionado, value="green", command=cambiar_color)
radio_verde.pack(pady=5)

radio_azul = tk.Radiobutton(root, text="Azul", variable=color_seleccionado, value="blue", command=cambiar_color)
radio_azul.pack(pady=5)


# Ejecutar la ventana principal

root.mainloop()
