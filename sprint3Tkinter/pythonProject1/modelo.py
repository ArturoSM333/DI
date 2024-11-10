import threading
import random
from PIL import Image, ImageTk
import requests
from io import BytesIO
from threading import Event

class GameModel:
    def __init__(self, difficulty):
        self.difficulty = difficulty
        self.board = []
    #    self.hidden_image = None
    #    self.images = {}
    #    self.images_loaded = Event()  # Usamos un Event para indicar cuando las imágenes están listas
        self._generate_board()

    def _generate_board(self):
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


'''
    def _load_images(self):
        url_base = "https://github.com/ArturoSM333/DI/tree/main/sprint3Tkinter/pythonProject1/Img/" 

        def load_images_thread():
            # Descargar la imagen oculta
            self.hidden_image = descargar_imagen(url_base + "hidden.png")

            # Obtener los identificadores de imágenes únicos
            unique_image_ids = []
            for row in self.board:
                for image_id in row:
                    if image_id not in unique_image_ids:
                        unique_image_ids.append(image_id)

            # Descargar cada imagen única
            for image_id in unique_image_ids:
                image_url = f"{url_base}{image_id}.png"
                self.images[image_id] = descargar_imagen(image_url)

            # Marcar que las imágenes se han cargado
            self.images_loaded.set()

        # Iniciar el hilo para cargar las imágenes
        threading.Thread(target=load_images_thread, daemon=True).start()
'''