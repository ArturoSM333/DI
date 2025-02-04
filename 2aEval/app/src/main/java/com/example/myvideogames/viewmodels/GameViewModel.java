package com.example.myvideogames.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myvideogames.models.Game;

import java.util.List;

public class GameViewModel extends ViewModel {
    private MutableLiveData<List<Game>> games = new MutableLiveData<>();

    public LiveData<List<Game>> getGames() {
        return games;
    }

    public void loadGames() {
        // Cargar juegos de Firebase aquí
    }
}
