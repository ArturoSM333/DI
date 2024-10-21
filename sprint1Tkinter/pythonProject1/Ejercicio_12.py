'''
Ejercicio 12: Aplicación de Registro de Usuarios
Desarrolla una aplicación que permita registrar usuarios con los siguientes datos: nombre,
edad y género. La aplicación debe contar con las siguientes funcionalidades:
1. Un campo de entrada (Entry) para el nombre del usuario.
2. Una barra deslizante (Scale) para seleccionar la edad del usuario (entre 0 y 100
años).
3. Tres botones de opción (Radiobutton) para seleccionar el género del usuario
(masculino, femenino, otro).
4. Un botón (Button) para añadir el usuario a una lista.
5. Una lista de usuarios (Listbox) que muestre el nombre, la edad y el género de los
usuarios registrados.
6. Una barra de desplazamiento vertical (Scrollbar) para la lista de usuarios.
7. Un botón para eliminar el usuario seleccionado de la lista.
8. Un botón de salir que cierre la aplicación.
9. Un menú desplegable con las opciones “Guardar Lista” y “Cargar Lista” esto nos
mostrara un messagebox indicando que la lista ha sido guardada o cargada.
'''

import tkinter as tk
from tkinter import messagebox

# Crear la ventana principal
root = tk.Tk()
root.title("Registro de Usuarios")
root.geometry("400x600")

# Etiqueta y campo de entrada para el nombre
tk.Label(root, text="Nombre:").pack(pady=5)
nombre_entry = tk.Entry(root)
nombre_entry.pack(pady=5)


# Barra deslizante para seleccionar la edad
tk.Label(root, text="Edad:").pack(pady=5)
escala = tk.Scale(root, from_=0, to=100, orient='horizontal')
escala.pack(pady=5)


# Variable para almacenar el género seleccionado
genero_usuario = tk.StringVar(value="Masculino")  # Valor por defecto

# Botones de opción para el género
genero_masc = tk.Radiobutton(root, text="Masculino", variable=genero_usuario, value="Masculino")
genero_fem = tk.Radiobutton(root, text="Femenino", variable=genero_usuario, value="Femenino")
genero_otro = tk.Radiobutton(root, text="Otro", variable=genero_usuario, value="Otro")

# Empaquetar los botones de opción
genero_masc.pack(pady=5)
genero_fem.pack(pady=5)
genero_otro.pack(pady=5)


# Crear la lista de usuarios
lista_usuarios = tk.Listbox(root)
lista_usuarios.pack(pady=10)


# Función para agregar un usuario a la lista
def agregar_usuario():
    nombre = nombre_entry.get()
    edad = escala.get()
    genero = genero_usuario.get()

    # Verificar que el nombre no esté vacío
    if nombre:
        usuario_info = f"{nombre} - {edad} años - {genero}"   # Formato para mostrar los datos en la lista
        lista_usuarios.insert(tk.END, usuario_info)  # Añadir usuario a la lista
    else:
        tk.messagebox.showwarning("Error", "El campo de nombre está vacío")


# Crear el botón para registrar usuarios
crear_usuario = tk.Button(root, text = "Crear Usuario", command=agregar_usuario)
crear_usuario.pack(pady=5)


# Función para eliminar el usuario seleccionado de la lista
def eliminar_usuario():
    seleccion = lista_usuarios.curselection()  # Obtener el índice seleccionado
    if seleccion:  # Verificar si hay un usuario seleccionado
        lista_usuarios.delete(seleccion)
    else:
        tk.messagebox.showwarning("Error", "No hay ningún usuario seleccionado")


# Botón para eliminar el usuario seleccionado
eliminar_usuario_boton = tk.Button(root, text="Eliminar Usuario", command=eliminar_usuario)
eliminar_usuario_boton.pack(pady=5)


# Función para salir de la aplicación
def salir():
    root.quit()


# Botón para salir de la aplicación
boton_salida = tk.Button(root, text="SALIR", command=salir)
boton_salida.pack(pady=5)


# Crear el menú principal
menu = tk.Menu(root)
root.config(menu=menu)


# Crear el submenú de "Archivo"
archivo_menu = tk.Menu(menu, tearoff=0)
menu.add_cascade(label="Archivo", menu=archivo_menu)


# Funciones para las opciones del menú
def guardar_lista():
    tk.messagebox.showinfo("Guardar Lista", "La lista ha sido guardada correctamente.")

def cargar_lista():
    tk.messagebox.showinfo("Cargar Lista", "La lista ha sido cargada correctamente.")


# Añadir opciones al submenú "Archivo"
archivo_menu.add_command(label="Guardar Lista", command=guardar_lista)
archivo_menu.add_command(label="Cargar Lista", command=cargar_lista)


# Ejecutamos la ventana principal
root.mainloop()