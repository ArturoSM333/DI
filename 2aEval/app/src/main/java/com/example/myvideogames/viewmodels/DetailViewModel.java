package com.example.myvideogames.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myvideogames.models.Game;

public class DetailViewModel extends ViewModel {
    // LiveData privado para almacenar el juego seleccionado
    private final MutableLiveData<Game> selectedGame = new MutableLiveData<>();

    // Método público para obtener el LiveData del juego seleccionado
    public LiveData<Game> getSelectedGame() {
        return selectedGame;
    }

    // Método público para establecer el juego seleccionado
    public void setSelectedGame(Game game) {
        selectedGame.setValue(game);  // Establece el valor del juego seleccionado
    }

}
