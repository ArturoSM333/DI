'''
Ejercicio 3: Entry
Crea una interfaz que tenga un campo de entrada (Entry) donde el usuario pueda escribir
su nombre. Al hacer clic en un botón, debe mostrarse un saludo personalizado en una
etiqueta.
'''

import tkinter as tk

# Creamos la ventana principal

root = tk.Tk()
root.title("Saludo personalizado")
root.geometry("300x200")


# Función para mostrar el saludo

def mostrar_saludo():
    nombre = entrada_nombre.get()
    if nombre:
        etiqueta.config(text=f"Hola, {nombre}!")
    else:
        etiqueta.config(text= "Ingresa tu nombre")


# Creo las etiquetas

etiqueta_1 = tk.Label(root, text="Ingresa tu nombre")
etiqueta_1.pack(pady=10)

etiqueta = tk.Label(text="")
etiqueta.pack(pady=5)


# Creo la Entry

entrada_nombre = tk.Entry(root, width=30)
entrada_nombre.pack(pady=5)


# Creo el botón

boton = tk.Button(root, text="Mostrar saludo", command=mostrar_saludo)
boton.pack(pady=10)

# Ejecuto la ventana
root.mainloop()