import tkinter as tk
from tkinter import messagebox, simpledialog
from modelo import GameModel
from vista import MainMenu, GameView
import time

class GameController:
    def __init__(self, root):
        self.root = root
        self.model = None
        self.selected = []
        self.timer_started = False

        self.menu = MainMenu(root, self.show_difficulty_selection, self.show_stats, root.quit)

    def show_difficulty_selection(self):
        difficulty = simpledialog.askstring("Dificultad", "Elige una dificultad: facil, medio, dificil")
        if difficulty in ["facil", "medio", "dificil"]:
            player_name = self.menu.ask_player_name()
            if player_name:
                self.start_game(difficulty)

    def start_game(self, difficulty):
        self.model = GameModel(difficulty, player_name="Jugador")
        self.view = GameView(self.on_card_click, self.update_move_count, self.update_time)
        self.view.create_board(self.model)

        if not self.timer_started:
            self.timer_started = True
            self.model.start_timer()
            self.update_time()

    def on_card_click(self, pos):
        if len(self.selected) < 2:
            self.selected.append(pos)
            self.view.update_board(pos, self.model.images[self.model.board[pos[0]][pos[1]]])

            if len(self.selected) == 2:
                self.handle_card_selection()

    def handle_card_selection(self):
        pos1, pos2 = self.selected
        if not self.model.check_match(pos1, pos2):
            self.root.after(1000, lambda: self.view.reset_cards(pos1, pos2))
        self.selected = []
        self.update_move_count()

        if self.model.is_game_complete():
            messagebox.showinfo("¡Ganaste!", "¡Has encontrado todas las parejas!")
            self.view.destroy()
            self.menu = MainMenu(self.root, self.show_difficulty_selection, self.show_stats, self.root.quit)

    def update_move_count(self):
        self.view.update_move_count(self.model.moves)

    def update_time(self):
        self.view.update_time(self.model.get_time())
        if not self.model.is_game_complete():
            self.root.after(1000, self.update_time)

    def show_stats(self):
        stats = self.model.load_scores()
        self.menu.show_stats(stats)
