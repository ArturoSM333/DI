import os
import tkinter as tk
from tkinter import simpledialog, messagebox
from tkinter import Toplevel, Label
from modelo import GameModel
from vista import MainMenu


class GameController:


    def __init__(self,root, vista):
        self.root=root
        self.vista = vista
        self.modelo = None
        self.board_frame = None
        self.selected = []
        self.vista.opcion_jugar.config(command=self.start_game_callback)
        self.vista.opcion_estadisticas.config(command=self.show_stats_callback)
        self.vista.opcion_salir.config(command=self.quit_callback)
        self.time = 0
        self.moves =0
        self.game_finished=False
        self.game_closed=False

    def start_game_callback(self):
        self.game_finished = True
        self.moves=0
        self.vista.move_label.config(text=f"Movimientos: {self.moves}")
        self.npares=0
        self.game_closed = True
        self.show_difficulty_selection()
        self.modelo = GameModel(self.difficulty)
        self.modelo.generate_board()  # Generar el tablero
        self.show_loading_window()
        self.game_finished = False
        self.start_timer()

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
        scores = self.load_scores()
        print(f"Mostrando estadísticas: {scores}")  # Imprimir las puntuaciones cargadas

        # Crear una nueva ventana para mostrar las estadísticas
        stats_window = Toplevel(self.vista.root)
        stats_window.title("Estadísticas")

        stats_label = tk.Label(stats_window, text="Mejores puntuaciones:", font=("Arial", 14))
        stats_label.pack(pady=10)

        for difficulty, entries in scores.items():
            stats_label = tk.Label(stats_window, text=f"Dificultad: {difficulty.capitalize()}", font=("Arial", 12))
            stats_label.pack(pady=5)
            for entry in entries:
                name, moves, time = entry
                stat_text = f"{name} - {moves} movimientos, {time} segundos"
                stat_label = tk.Label(stats_window, text=stat_text, font=("Arial", 10))
                stat_label.pack(pady=2)

        # Botón para cerrar la ventana de estadísticas
        close_button = tk.Button(stats_window, text="Cerrar", command=stats_window.destroy)
        close_button.pack(pady=10)

    def load_scores(self):
        scores = {"facil": [], "medio": [], "dificil": []}
        try:
            with open("Txt/Ranking.txt", "r") as file:
                for line in file:
                    name, difficulty, moves, time = line.strip().split(",")
                    if difficulty in scores:
                        scores[difficulty].append((name, int(moves), int(time)))
            # Ordenar por movimientos y tiempo
            for difficulty in scores:
                scores[difficulty] = sorted(scores[difficulty], key=lambda x: (x[1], x[2]))[:3]
            print(f"Estadísticas cargadas: {scores}")
        except Exception as e:
            print(f"Error al cargar las puntuaciones: {e}")
        return scores

    def quit_callback(self):
        print("Saliendo...")
        self.game_closed=True
        self.vista.root.quit()

    def start_timer(self):
        self.time=0
        self.game_finished = False  # Reiniciar el estado del juego
        self.update_time()


    def update_time(self):
        if not self.game_finished or not self.game_closed:
            self.vista.timer_label.config(text=f"Tiempo: {self.time} s")
            self.time+=1
            self.root.after(1000, self.update_time)



    def update_move_count(self):
        self.moves+=1
        self.vista.move_label.config(text=f"Movimientos: {self.moves}")

    def create_game_board(self):
        if not self.modelo.images_loaded.is_set():
            # Si las imágenes no están cargadas, no creamos el tablero.
            self.root.after(100, self.create_game_board)
            return

        print("Tablero creado con dificultad:", self.difficulty)

        if self.board_frame:
            self.board_frame.destroy()

        self.board_frame = tk.Frame(self.vista.root)
        self.board_frame.pack(fill=tk.BOTH, expand=True)

        # Determinar el número de filas y columnas según la dificultad
        if self.difficulty == "facil":
            rows, cols = 2, 4  # 2 filas, 4 columnas (8 cartas)
        elif self.difficulty == "medio":
            rows, cols = 4, 4  # 4 filas, 4 columnas (16 cartas)
        elif self.difficulty == "dificil":
            rows, cols = 4, 6  # 4 filas, 6 columnas (24 cartas)

        # Ajustar el tamaño de los botones según la pantalla
        self.buttons = {}
        for row_index in range(rows):
            row_frame = tk.Frame(self.board_frame)
            row_frame.pack(fill=tk.X)  # Asegura que cada fila se ajusta al ancho de la ventana
            for col_index in range(cols):
                image_name = f"card_{row_index}_{col_index}"  # Ejemplo de nombre de carta
                image = self.modelo.hidden_image

                # Crear un botón ajustable según el tamaño de la ventana
                button = tk.Button(row_frame, image=image,
                                   command=lambda row=row_index, col=col_index: self.on_card_click(row, col))
                button.pack(side="left", padx=5, pady=5)

                self.buttons[(row_index, col_index)] = button
                button.image = image

    def hide_cards(self):
        # Oculta las cartas volviendo a la imagen oculta
        for (row, col) in self.selected:
            card_id = self.modelo.board[row][col]
            self.buttons[(row, col)].config(image=self.modelo.hidden_image)
        self.selected.clear()

    def show_cards(self, card_id):
        image_card = self.modelo.images[card_id]
        self.buttons[card_id].config(image=image_card)

    def handle_card_selection(self):
        if len(self.selected) == 2:
            (row1, col1), (row2, col2) = self.selected
            card1_id = self.modelo.board[row1][col1]
            card2_id = self.modelo.board[row2][col2]
            # Si las dos cartas seleccionadas son iguales
            if card1_id == card2_id:
                self.npares += 1
                if self.difficulty == "facil" and self.npares == 4:
                    messagebox.showinfo("Enhorabuena!", "¡Has completado el nivel fácil!")
                    self.game_over()
                elif self.difficulty == "medio" and self.npares == 8:
                    messagebox.showinfo("Enhorabuena!", "¡Has completado el nivel medio!")
                    self.game_over()
                elif self.difficulty == "dificil" and self.npares == 12:
                    messagebox.showinfo("Enhorabuena!", "¡Has completado el nivel difícil!")
                    self.game_over()
                self.selected.clear()
            else:
                self.root.after(500, self.hide_cards)

    def update_board(self, row, col, image):
        # Actualiza la imagen del botón correspondiente
        self.buttons[(row, col)].config(image=image)

    def on_card_click(self, row, col):
        # Lógica cuando se hace clic en una carta
        card_id = self.modelo.board[row][col]  # Obtener el ID de la carta según su fila y columna
        print(f"Carta {card_id} clickeada en la posición ({row}, {col})")

        # Evitar seleccionar la misma carta dos veces
        if len(self.selected) < 2:
            self.selected.append((row, col))  # Almacenar la posición (fila, columna)
            image = self.modelo.images.get(card_id)  # Obtener la imagen correspondiente
            self.update_board(row, col, image)

            # Cuando se hayan seleccionado dos cartas, verificamos si son una pareja
            if len(self.selected) == 2:
                # Esperamos 2 segundos para permitir que el jugador vea las cartas antes de comprobar
                self.root.after(1000, self.handle_card_selection)
                self.update_move_count()


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

    def game_over(self):
        # El juego ha terminado
        self.game_finished = True
        self.save_score()  # Guardar la puntuación del jugador
        messagebox.showinfo("¡Felicidades!",
                            f"Has completado el juego en {self.moves} movimientos y {self.time} segundos.")
        self.show_stats_callback()  # Mostrar estadísticas al finalizar

    def save_score(self):
        try:
            if not os.path.exists("Txt"):
                os.makedirs("Txt")

            with open("Txt/Ranking.txt", "a") as file:
                # Guardar la puntuación
                file.write(f"{self.player_name},{self.difficulty},{self.moves},{self.time}\n")
            print("Puntuación guardada correctamente.")
        except Exception as e:
            print(f"Error al guardar la puntuación: {e}")


