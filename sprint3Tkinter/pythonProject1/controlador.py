import tkinter as tk
from tkinter import simpledialog, messagebox
from tkinter import Toplevel, Label
from modelo import GameModel


class GameController:


    def __init__(self, vista):
        self.vista = vista
        self.modelo = None
        self.board_frame = None

        self.vista.opcion_jugar.config(command=self.start_game_callback)
        self.vista.opcion_estadisticas.config(command=self.show_stats_callback)
        self.vista.opcion_salir.config(command=self.quit_callback)

    def start_game_callback(self):
        self.show_difficulty_selection()
        self.modelo = GameModel(self.difficulty)
        self.modelo.generate_board()  # Generar el tablero
        self.show_loading_window()


    def show_difficulty_selection(self):
        # Bucle para asegurarse de que la dificultad es válida
        while True:
            # Solicitar la dificultad al jugador
            difficulty = simpledialog.askstring(
                "Seleccionar Dificultad",
                "Elige la dificultad: 'facil', 'medio' o 'dificil'",
                parent=self.vista.root
            )

            # Verificar si la entrada es válida
            if difficulty in ["facil", "medio", "dificil"]:
                self.difficulty = difficulty
                break
            else:
                # Mostrar un mensaje de error si la dificultad no es válida
                messagebox.showerror(
                    "Entrada Inválida",
                    "Por favor, elige una dificultad válida: 'facil', 'medio' o 'dificil'."
                )

        # Solicitar el nombre del jugador
        self.player_name = self.vista.ask_player_name()
        print(f"Dificultad seleccionada: {self.difficulty}")
        print(f"Nombre del jugador: {self.player_name}")

    def show_stats_callback(self):
        print("h")

    def quit_callback(self):
        self.vista.root.quit()

    def create_game_board(self):
        print("Tablero creado con dificultad:", self.difficulty)
    #    print("Tablero:", self.modelo.board)


        if self.board_frame:
            self.board_frame.destroy()

        self.board_frame = tk.Frame(self.vista.root)
        self.board_frame.pack()

        # Crear los botones para las cartas del tablero
        for row in self.modelo.board:
            row_frame = tk.Frame(self.board_frame)
            row_frame.pack(pady=5)
            for card_id in row:
                image_name = f"as_{card_id}.jpg"  # Formato de imagen que deberías tener
                # Intentar obtener la imagen desde el diccionario de imágenes
                image = self.modelo.images.get(image_name, self.modelo.hidden_image)

                # Crear un botón para cada carta
                button = tk.Button(row_frame, image=image,
                                   command=lambda card_id=card_id: self.card_click_callback(card_id))
                button.pack(side="left")

                # Guardar la referencia de la imagen para evitar que se libere
                button.image = image

    def card_click_callback(self, card_id):
        # Lógica cuando se hace clic en una carta
        print(f"Carta {card_id} clickeada")


    def show_loading_window(self):
        # Crear una ventana de carga
        loading_window = Toplevel(self.vista.root)
        loading_window.title("Cargando Imágenes")
        Label(loading_window, text="Cargando imágenes, por favor espera...").pack()

        # Iniciar la carga de imágenes en un hilo
        self.modelo.load_images()


        # Verificar periódicamente si las imágenes se han cargado
        def check_images_loaded():
            if self.modelo.images_loaded.is_set():
                loading_window.destroy()
                self.create_game_board()  # Llamar a la función para crear el tablero
            else:
                self.vista.root.after(100, check_images_loaded)

        check_images_loaded()


