import tkinter as tk

class GameController:


    def __init__(self, vista):
        self.vista = vista

        self.vista.opcion_jugar.config(command=self.start_game_callback)
        self.vista.opcion_estadisticas.config(command=self.show_stats_callback)
        self.vista.opcion_salir.config(command=self.quit_callback)

    def start_game_callback(self):
        print("")
    def show_stats_callback(self):
        print("h")

    def quit_callback(self):
        self.vista.root.quit()

