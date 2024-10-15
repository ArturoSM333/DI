'''
Ejercicio 7: Canvas
Crea un Canvas y dibuja en él un círculo y un rectángulo. El tamaño y las posiciones
deben ser definidas por el usuario a través de dos campos de entrada (Entry) para las
coordenadas.
'''

import tkinter as tk

# Crear la ventana principal

root = tk.Tk()
root.title("Dibujar Figuras")
root.geometry("600x400")


# Crear un Canvas

canvas = tk.Canvas(root, width=300, height=200, bg="white")
canvas.grid(pady=20)


# Etiqueta y entradas para las coordenadas del círculo

etiqueta_circulo = tk.Label(root, text="Círculo - Ingresa coordenadas (x, y) y radio:")
etiqueta_circulo.grid(row=1,pady = 5, column=0)

entrada_x = tk.Entry(root, width=5)
entrada_x.grid(row=1, pady=5, padx=5,column=1)
entrada_x.insert(0, "X")

entrada_y = tk.Entry(root, width=5)
entrada_y.grid(row=1,pady=5, padx=5,column=2)
entrada_y.insert(0, "Y")

entrada_radio = tk.Entry(root, width=5)
entrada_radio.grid(row=1, pady=5, padx=5,column=3)
entrada_radio.insert(0, "Radio")


# Etiqueta y entradas para las coordenadas del rectángulo

etiqueta_rectangulo = tk.Label(root, text="Rectángulo - Ingresa (x1, y1) y (x2, y2):")
etiqueta_rectangulo.grid(row=2,pady=5, padx=5, column=0)

entrada_x1 = tk.Entry(root, width=5)
entrada_x1.grid(row=2, pady=5, padx=5, column=1)
entrada_x1.insert(0, "X1")

entrada_y1 = tk.Entry(root, width=5)
entrada_y1.grid(row=2,pady=5, padx=5, column=2)
entrada_y1.insert(0, "Y1")

entrada_x2 = tk.Entry(root, width=5)
entrada_x2.grid(row=2, pady=5, padx=5,column =3)
entrada_x2.insert(0, "X2")

entrada_y2 = tk.Entry(root, width=5)
entrada_y2.grid(row=2, padx=5, pady=5,column=4)
entrada_y2.insert(0, "Y2")


# Función para dibujar un círculo

def dibujar_circulo():
     x = int(entrada_x.get())
     y = int(entrada_y.get())
     radio = int(entrada_radio.get())
     canvas.create_oval(x - radio, y - radio, x + radio, y + radio, outline="blue", width=2)


# Función para dibujar un rectángulo

def dibujar_rectangulo():
    x1 = int(entrada_x1.get())
    y1 = int(entrada_y1.get())
    x2 = int(entrada_x2.get())
    y2 = int(entrada_y2.get())
    canvas.create_rectangle(x1, y1, x2, y2, outline="red", width=2)


# Botón para dibujar el círculo

boton_circulo = tk.Button(root, text="Dibujar Círculo", command=dibujar_circulo)
boton_circulo.grid(row=1,pady=10,column=6)


# Botón para dibujar el rectángulo

boton_rectangulo = tk.Button(root, text="Dibujar Rectángulo", command=dibujar_rectangulo)
boton_rectangulo.grid(row=2, pady=10, column=6)


# Ejecutar la ventana principal

root.mainloop()
