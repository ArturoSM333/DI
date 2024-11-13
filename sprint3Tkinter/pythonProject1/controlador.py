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
        self.npares=0
        self.game_closed = True
        self.show_difficulty_selection()
        self.modelo = GameModel(self.difficulty)
        self.modelo.generate_board()  # Generar el tablero
        self.show_loading_window()
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
        print("Estadísticas")

    def quit_callback(self):
        print("Saliendo...")
        self.game_closed=True
        self.vista.root.quit()

    def start_timer(self):
        self.time=0
        self.update_time()


    def update_time(self):
        self.vista.timer_label.config(text=f"Tiempo: {self.time} s")
        self.time+=1
        if not self.game_finished or not self.game_closed:
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
    #    print("Tablero:", self.modelo.board)

        if self.board_frame:
            self.board_frame.destroy()

        self.board_frame = tk.Frame(self.vista.root)
        self.board_frame.pack()

        # Crear los botones para las cartas del tablero
        self.buttons = {}
        for row_index, row in enumerate(self.modelo.board):
            row_frame = tk.Frame(self.board_frame)
            row_frame.pack(pady=5)
            for col_index, card_id in enumerate(row):
                image_name = card_id
                # Intentar obtener la imagen desde el diccionario de imágenes
                image = self.modelo.hidden_image
                # Crear un botón para cada carta
                button = tk.Button(row_frame, image=image,
                                   command=lambda row=row_index, col=col_index: self.on_card_click(row, col))
                button.pack(side="left")

                # Guardar el botón en el diccionario con las tuplas (row, col) como clave
                self.buttons[(row_index, col_index)] = button

                # Guardar la referencia de la imagen para evitar que se libere
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
                elif self.difficulty == "medio" and self.npares == 8:
                    messagebox.showinfo("Enhorabuena!", "¡Has completado el nivel medio!")
                elif self.difficulty == "dificil" and self.npares == 12:
                    messagebox.showinfo("Enhorabuena!", "¡Has completado el nivel difícil!")
                self.selected.clear()
            else:
                self.root.after(1000, self.hide_cards)

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
                self.root.after(2000, self.handle_card_selection)
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

'''    # Cargar la imagen con Pillow
    imagen = Image.open("ruta/a/tu/imagen.jpg")  # Reemplaza con el path de tu imagen

    # Convertir la imagen a un formato compatible con Tkinter
    imagen_tk = ImageTk.PhotoImage(imagen)'''