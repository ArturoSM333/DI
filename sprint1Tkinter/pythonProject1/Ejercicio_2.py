'''
Ejercicio 2: Button
Diseña una ventana con dos botones. Uno debe mostrar un mensaje en una etiqueta al
presionarlo, y el otro debe cerrar la ventana cuando se haga clic en él.
'''

import tkinter as tk

# Crear la ventana principal

root = tk.Tk()
root.title("Ventana con dos botones")
root.geometry("300x150")


# Función para mostrar un mensaje en la etiqueta

def mostrar_mensaje():
    etiqueta.config(text="¡Botón presionado!")


# Función para cerrar la ventana

def cerrar_ventana():
    root.destroy()


# Creamos una etiqueta

etiqueta = tk.Label(root, text="Presiona el botón para ver el mensaje")
etiqueta.pack(pady=10)


# Crear el primer botón que muestra el mensaje

boton_mensaje = tk.Button(root, text="Mostrar mensaje", command=mostrar_mensaje)
boton_mensaje.pack(side="left", padx=20)


# Crear el segundo botón que cierra la ventana

boton_cerrar = tk.Button(root, text="Cerrar ventana", command=cerrar_ventana)
boton_cerrar.pack(side="right", padx=20)


# Ejecutar la ventana

root.mainloop()
