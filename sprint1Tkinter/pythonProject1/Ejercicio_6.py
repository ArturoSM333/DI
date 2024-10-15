'''
Ejercicio 6: Listbox
Crea una lista de elementos (Listbox) que contenga una lista de frutas (por ejemplo,
“Manzana”, “Banana”, “Naranja”). Al seleccionar una fruta y hacer clic en un botón,
muestra el nombre de la fruta seleccionada en una etiqueta.
'''

import tkinter as tk

# Crear la ventana principal

root = tk.Tk()
root.title("Lista de Frutas")
root.geometry("300x300")


# Función para mostrar la fruta seleccionada

def mostrar_fruta():
    # Obtener la selección de la lista
    seleccion = listbox_frutas.curselection()
    if seleccion:  # Verificar que haya una selección
        fruta = listbox_frutas.get(seleccion)
        etiqueta.config(text=f"Fruta seleccionada: {fruta}")
    else:
        etiqueta.config(text="No has seleccionado ninguna fruta.")


# Crear una lista de frutas

frutas = ["Manzana", "Banana", "Naranja", "Fresa"]


# Crear un Listbox para mostrar las frutas

listbox_frutas = tk.Listbox(root)
for fruta in frutas:
    listbox_frutas.insert(tk.END, fruta)  # Insertar cada fruta en el Listbox
listbox_frutas.pack(pady=10)


# Crear un botón que muestra la fruta seleccionada

boton_mostrar = tk.Button(root, text="Mostrar fruta", command=mostrar_fruta)
boton_mostrar.pack(pady=10)


# Crear una etiqueta para mostrar la fruta seleccionada

etiqueta = tk.Label(root, text="No has seleccionado ninguna fruta.")
etiqueta.pack(pady=10)


# Ejecutar la ventana

root.mainloop()
