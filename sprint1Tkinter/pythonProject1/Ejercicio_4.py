'''
Ejercicio 4: Checkbutton
Crea una ventana con tres casillas de verificación (Checkbutton) que representen tres
aficiones (por ejemplo, “Leer”, “Deporte”, “Música”). Cuando el usuario seleccione o
deseleccione una casilla, el estado actual de las aficiones seleccionadas debe mostrarse
en una etiqueta.
'''

import tkinter as tk

# Creamos la ventana principal

root = tk.Tk()
root.title("Aficiones")
root.geometry("300x250")


# Variables para almacenar el estado de cada casilla de verificación

aficion_leer = tk.IntVar()
aficion_deporte = tk.IntVar()
aficion_musica = tk.IntVar()


# Función para actualizar el estado de las aficiones seleccionadas

def actualizar_aficiones():
    seleccion = []
    if aficion_leer.get() == 1:
        seleccion.append("Leer")
    if aficion_deporte.get() == 1:
        seleccion.append("Deporte")
    if aficion_musica.get() == 1:
        seleccion.append("Música")

    if seleccion:
        etiqueta.config(text=f"Aficiones seleccionadas: {', '.join(seleccion)}")
    else:
        etiqueta.config(text="No has seleccionado ninguna afición.")


# Crear los checkbuttons para las aficiones

check_leer = tk.Checkbutton(root, text="Leer", variable=aficion_leer, command=actualizar_aficiones)
check_leer.pack(pady=5)

check_deporte = tk.Checkbutton(root, text="Deporte", variable=aficion_deporte, command=actualizar_aficiones)
check_deporte.pack(pady=5)

check_musica = tk.Checkbutton(root, text="Música", variable=aficion_musica, command=actualizar_aficiones)
check_musica.pack(pady=5)


# Crear una etiqueta para mostrar el estado de las aficiones seleccionadas

etiqueta = tk.Label(root, text="No has seleccionado ninguna afición.")
etiqueta.pack(pady=20)


# Ejecutar la ventana
root.mainloop()

