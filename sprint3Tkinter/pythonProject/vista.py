import tkinter as tk
from tkinter import simpledialog, Toplevel

class GameView:
    def __init__(self, on_card_click_callback, update_move_count_callback, update_time_callback):
        self.window = Toplevel()
        self.window.title("Tablero de Juego")
        self.labels = []
        self.on_card_click_callback = on_card_click_callback
        self.update_move_count_callback = update_move_count_callback
        self.update_time_callback = update_time_callback

    def create_board(self, model):
        for r, row in enumerate(model.board):
            label_row = []
            for c, _ in enumerate(row):
                label = tk.Label(self.window, image=model.hidden_image)
                label.grid(row=r, column=c)
                label.bind("<Button-1>", lambda e, pos=(r, c): self.on_card_click_callback(pos))
                label_row.append(label)
            self.labels.append(label_row)

        self.move_label = tk.Label(self.window, text="Movimientos: 0")
        self.move_label.pack()
        self.time_label = tk.Label(self.window, text="Tiempo: 0 s")
        self.time_label.pack()

    def update_board(self, pos, image_id):
        r, c = pos
        self.labels[r][c].config(image=image_id)

    def reset_cards(self, pos1, pos2):
        r1, c1 = pos1
        r2, c2 = pos2
        self.labels[r1][c1].config(image=self.model.hidden_image)
        self.labels[r2][c2].config(image=self.model.hidden_image)

    def update_move_count(self, moves):
        self.move_label.config(text=f"Movimientos: {moves}")

    def update_time(self, time):
        self.time_label.config(text=f"Tiempo: {time} s")

    def destroy(self):
        self.window.destroy()

class MainMenu:
    def __init__(self, root, start_game_callback, show_stats_callback, quit_callback):
        self.frame = tk.Frame(root)
        self.frame.pack()

        self.start_button = tk.Button(self.frame, text="Jugar", command=start_game_callback)
        self.start_button.pack()
        self.stats_button = tk.Button(self.frame, text="Estadísticas", command=show_stats_callback)
        self.stats_button.pack()
        self.quit_button = tk.Button(self.frame, text="Salir", command=quit_callback)
        self.quit_button.pack()

    def ask_player_name(self):
        return simpledialog.askstring("Nombre del Jugador", "Por favor, ingresa tu nombre:")

    def show_stats(self, stats):
        # Mostrar estadísticas en un diálogo
        pass
