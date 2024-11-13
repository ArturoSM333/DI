import datetime
import threading
import random
from threading import Event
from recursos import descargar_imagen


class GameModel:
    def __init__(self, difficulty):
        self.difficulty = difficulty
        self.board = []
        self.timer_started = False  # Controla si el temporizador ya ha comenzado
        self.time_elapsed = 0  # El tiempo transcurrido en segundos
        self.hidden_image = None
        self.images = {}
        self.images_loaded = Event()  # Usamos un Event para indicar cuando las imágenes están listas
        self.generate_board()

    def generate_board(self):
        if self.difficulty == "facil":
            num_pares = 4
        elif self.difficulty == "medio":
            num_pares = 8
        elif self.difficulty == "dificil":
            num_pares = 12

        # Creamos una lista de cartas emparejadas y mezclarlas aleatoriamente
        cartas = list(range(1, num_pares + 1)) * 2
        random.shuffle(cartas)
        self.board = [cartas[i:i + 4] for i in range(0, len(cartas), 4)]  # Dividir en filas de 4 cartas



    def load_images(self):
        url_base = "https://raw.githubusercontent.com/ArturoSM333/DI/refs/heads/main/sprint3Tkinter/pythonProject1/Img/"
        def load_images_thread():

            # Descargar la imagen oculta
            self.hidden_image = descargar_imagen(url_base + "hidden.jpg", (100, 100))

            # Descargar cada imagen única
            for image_id in range(12):
                image_url = url_base + "imagen_" + str(image_id) + ".jpg"
                self.images[image_id] = descargar_imagen(image_url, (100, 100))

            # Marcar que las imágenes se han cargado
            self.images_loaded.set()

        # Iniciar el hilo para cargar las imágenes
        threading.Thread(target=load_images_thread, daemon=True).start()


    def save_score(self):
        try:
            with open("ranking.txt", "a") as file:
                file.write(f"{self.player_name},{self.difficulty},{self.moves},{datetime.now()}\n")
        except Exception as e:
            print(f"Error al guardar la puntuación: {e}")

    def load_scores(self):
        scores = {"facil": [], "medio": [], "dificil": []}
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
