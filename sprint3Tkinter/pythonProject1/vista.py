
import tkinter as tk



class MainMenu:

    #Inicializamos la ventana principal

    def __init__(self, root):
        self.root = root
        self.root.title("Sprint 3 Tkinter")
        self.root.geometry("600x600")

        # Creareamos ahora los botones del menú principal

        self.opcion_jugar = tk.Button(self.root, text="Jugar")
        self.opcion_jugar.pack(pady=5)

        self.opcion_estadisticas = tk.Button(self.root, text="Estadísticas")
        self.opcion_estadisticas.pack(pady=5)

        self.opcion_salir = tk.Button(self.root, text="Salir")
        self.opcion_salir.pack(pady=5)
