'''
Ejercicio 9: Menu
Crea una barra de menú en la ventana que tenga dos menús desplegables: “Archivo” con
las opciones “Abrir” y “Salir”, y “Ayuda” con la opción “Acerca de”. La opción “Salir” debe
cerrar la ventana, y “Acerca de” debe mostrar un mensaje informativo en una ventana
emergente.
'''

import tkinter as tk
from tkinter import messagebox

# Creamos la ventana principal

root = tk.Tk()
root.title = "Menú"
root.geometry ="300x300"


# Creamos la barra de menú

barra_menu = tk.Menu(root)
root.config(menu=barra_menu)


# Métodos

def salir():
    root.quit()

def mensaje_informativo():
    messagebox.showinfo("Ayuda", "Mensaje informativo.")


# Menú 'Archivo'

menu_archivo = tk.Menu(barra_menu, tearoff=0)
menu_archivo.add_command(label="Abrir")
menu_archivo.add_separator()
menu_archivo.add_command(label="Salir", command=salir)
barra_menu.add_cascade(label="Archivo", menu=menu_archivo)


# Menú Ayuda

menu_ayuda = tk.Menu(barra_menu, tearoff=0)
menu_ayuda.add_command(label="Acerca de", command=mensaje_informativo)
barra_menu.add_cascade(label="Ayuda", menu=menu_ayuda)


# Iniciar el bucle de la ventana

root.mainloop()