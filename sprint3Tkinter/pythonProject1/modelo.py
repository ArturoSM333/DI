import threading
import time
import random
import datetime
from threading import Event
from recursos import descargar_imagen


class GameModel:
    def __init__(self, difficulty, player_name, cell_size =100):
        self.difficulty = difficulty
        self.board = []
        self.timer_started = False  # Controla si el temporizador ya ha comenzado
        self.time_elapsed = 0  # El tiempo transcurrido en segundos
        self.start_time=0
        self.moves= 0
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

            # Identificadores de imágenes únicos
            unique_image_ids = ["as_bastos.jpg", "as_copas.jpg", "as_corazones.jpg", "as_diamantes.jpg", "as_espadas.jpg", "as_oros.jpg", "as_trebol.jpg"]

            # Descargar cada imagen única
            for image_id in unique_image_ids:
                image_url = url_base + image_id
                self.images[image_id] = descargar_imagen(image_url, (100, 100))

            # Marcar que las imágenes se han cargado
            self.images_loaded.set()

        # Iniciar el hilo para cargar las imágenes
        threading.Thread(target=load_images_thread, daemon=True).start()

    '''
    def start_timer(self):
        if not self.timer_started:
            self.timer_started = True
            self.time_elapsed = 0  # Reiniciar el tiempo
            self.update_time()


    def update_time(self):
        self.time_elapsed += 1  # Incrementar el tiempo en 1 segundo
        self.vista.update_timer(self.time_elapsed)  # Actualiza la vista con el tiempo

        # Llamar nuevamente a update_time después de 1000ms (1 segundo)
        self.vista.root.after(1000, self.update_time)  # Recurre cada 1 segundo


    def stop_timer(self):
        """Detener el temporizador cuando se complete el juego"""
        self.timer_started = False
        print(f"Juego completado. Tiempo total: {self.time_elapsed} segundos.")
'''