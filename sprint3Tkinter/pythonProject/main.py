import tkinter as tk
from controlador import GameController
from modelo import GameModel

if __name__ == "__main__":
    root = tk.Tk()
    root.title("Juego de Memoria")
    root.geometry("300x300")
    # Inicialización del modelo con dificultad y nombre del jugador
    modelo = GameModel(difficulty="facil", player_name="Jugador")

    # Inicialización del controlador
    controller = GameController(root)

    # Inicio del bucle principal de la interfaz
    root.mainloop()
