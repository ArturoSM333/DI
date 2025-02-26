package com.example.myvideogames.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myvideogames.models.Game;

import java.util.List;

public class GameViewModel extends ViewModel {
    // LiveData que almacena la lista de juegos
    private MutableLiveData<List<Game>> games = new MutableLiveData<>();

    // Método para obtener el LiveData de juegos (para que la vista pueda observarlo)
    public LiveData<List<Game>> getGames() {
        return games;
    }

    // Método para cargar los juegos (deberías agregar la lógica para cargar desde Firebase u otra fuente)
    public void loadGames() {
        // Cargar juegos de Firebase aquí
    }
}
