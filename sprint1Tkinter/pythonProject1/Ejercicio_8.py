'''
Ejercicio 8: Frame
Diseña una interfaz que esté dividida en dos Frame. El Frame superior debe contener dos
etiquetas y un campo de entrada, mientras que el Frame inferior debe contener dos
botones: uno para mostrar el contenido del Entry en una etiqueta, y otro para borrar el
contenido del Entry.
'''

import tkinter as tk

# Creamos la ventana principal

root = tk.Tk()
root.title("Interfaz con 2 frames")
root.geometry("300x300")


# Frame superior

frame_superior = tk.Frame(root)
frame_superior.pack(pady=10)


# Etiqueta 1

etiqueta_1 = tk.Label(frame_superior, text="Ingresa tu nombre:")
etiqueta_1.pack(pady=5)


# Campo de entrada

entrada_nombre = tk.Entry(frame_superior, width=30)
entrada_nombre.pack(pady=5)


# Etiqueta 2 para mostrar el resultado

etiqueta_resultado = tk.Label(frame_superior, text="")
etiqueta_resultado.pack(pady=5)


# Frame inferior

frame_inferior = tk.Frame(root)
frame_inferior.pack(pady=10)


# Función para mostrar el contenido del Entry

def mostrar_nombre():
    nombre = entrada_nombre.get()
    etiqueta_resultado.config(text=f"Hola, {nombre}!")


# Función para borrar el contenido del Entry

def borrar_nombre():
    entrada_nombre.delete(0, tk.END)
    etiqueta_resultado.config(text="")


# Botón para mostrar el nombre

boton_mostrar = tk.Button(frame_inferior, text="Mostrar Nombre", command=mostrar_nombre)
boton_mostrar.pack(side=tk.LEFT, padx=5)


# Botón para borrar el nombre

boton_borrar = tk.Button(frame_inferior, text="Borrar Nombre", command=borrar_nombre)
boton_borrar.pack(side=tk.LEFT, padx=5)


# Ejecutar la ventana principal

root.mainloop()
