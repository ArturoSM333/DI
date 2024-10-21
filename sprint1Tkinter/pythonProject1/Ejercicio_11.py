'''
Ejercicio 11: Scale
Crea una barra deslizante (Scale) que permita al usuario seleccionar un número entre 0 y
100. El valor seleccionado debe mostrarse en tiempo real en una etiqueta.
'''

import tkinter as tk


# Creamos la ventana principal

root = tk.Tk()
root.title("Scale")
root.geometry("300x150")


# Actualizarr valor

def actualizar_valor(valor):
    etiqueta.config(text=f"Valor: {valor}")


# Etiqueta para mostrar el valor

etiqueta = tk.Label(root, text="Valor: 0")
etiqueta.pack(pady=10)


# Crear Scale

scale = tk.Scale(root, from_ = 0, to = 100, orient = 'horizontal', command=actualizar_valor)
scale.pack(pady=10)


# Bucle principal

root.mainloop()