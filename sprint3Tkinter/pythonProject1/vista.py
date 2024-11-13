
import tkinter as tk
from tkinter import simpledialog


class MainMenu:

    #Inicializamos la ventana principal

    def __init__(self, root):
        self.root = root
        self.root.title("Sprint 3 Tkinter")
        self.root.geometry("200x200")

        # Creareamos ahora los botones del menú principal

        self.opcion_jugar = tk.Button(self.root, text="Jugar")
        self.opcion_jugar.pack(pady=5)

        self.opcion_estadisticas = tk.Button(self.root, text="Estadísticas")
        self.opcion_estadisticas.pack(pady=5)

        self.opcion_salir = tk.Button(self.root, text="Salir")
        self.opcion_salir.pack(pady=5)


        self.timer_label = tk.Label(self.root, text="Tiempo: 0")
        self.timer_label.pack()

        self.move_label = tk.Label(self.root, text="Movimientos: 0")
        self.move_label.pack()



    def ask_player_name(self):
        # Mostrar un cuadro de diálogo para pedir el nombre del jugador
        player_name = simpledialog.askstring(
            "Nombre del Jugador",
            "Introduce tu nombre:",
            parent=self.root
        )
        return player_name



