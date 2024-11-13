import tkinter as tk
from vista import MainMenu
from controlador import GameController

if __name__ == '__main__':
    root=tk.Tk()


    vista=MainMenu(root)
    controlador = GameController(vista)

    root.mainloop()


