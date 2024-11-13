import threading
import time
import random
from datetime import datetime
from recursos import descargar_imagen


class GameModel:
    def __init__(self, difficulty, player_name, cell_size=100):
        self.difficulty = difficulty
        self.player_name = player_name
        self.cell_size = cell_size
        self.start_time = None
        self.moves = 0
        self.pairs_found = 0
        self.images_loaded = threading.Event()
        self.board = self._generate_board()
        self.hidden_image = None
        self.images = {}
        self._load_images()

    def _generate_board(self):
        num_pairs = {"fácil": 8, "medio": 18, "difícil": 32}[self.difficulty]
        ids = list(range(num_pairs)) * 2
        random.shuffle(ids)
        return [ids[i:i + int(len(ids) ** 0.5)] for i in range(0, len(ids), int(len(ids) ** 0.5))]

    def _load_images(self):
        def load():
            url_base = "https://raw.githubusercontent.com/ArturoSM333/DI/refs/heads/main/sprint3Tkinter/pythonProject/Img/"
            self.hidden_image = descargar_imagen(url_base + "hidden.jpg", (self.cell_size, self.cell_size))
            for i in range(len(self.board) ** 2 // 2):
                self.images[i] = descargar_imagen(url_base + f"imagen_{i}.jpg", (self.cell_size, self.cell_size))
            self.images_loaded.set()

        threading.Thread(target=load).start()

    def images_are_loaded(self):
        return self.images_loaded.is_set()

    def start_timer(self):
        self.start_time = time.time()

    def get_time(self):
        return int(time.time() - self.start_time)

    def check_match(self, pos1, pos2):
        self.moves += 1
        match = self.board[pos1[0]][pos1[1]] == self.board[pos2[0]][pos2[1]]
        if match:
            self.pairs_found += 1
        return match

    def is_game_complete(self):
        return self.pairs_found == len(self.board) ** 2 // 2

    def save_score(self):
        try:
            with open("ranking.txt", "a") as file:
                file.write(f"{self.player_name},{self.difficulty},{self.moves},{datetime.now()}\n")
        except Exception as e:
            print(f"Error al guardar la puntuación: {e}")

    def load_scores(self):
        scores = {"fácil": [], "medio": [], "difícil": []}
        try:
            with open("ranking.txt", "r") as file:
                for line in file:
                    name, difficulty, moves, date = line.strip().split(",")
                    if difficulty in scores:
                        scores[difficulty].append((name, int(moves), date))
            for difficulty in scores:
                scores[difficulty] = sorted(scores[difficulty], key=lambda x: x[1])[
                                     :3]  # Solo las 3 mejores puntuaciones
        except Exception as e:
            print(f"Error al cargar las puntuaciones: {e}")
        return scores