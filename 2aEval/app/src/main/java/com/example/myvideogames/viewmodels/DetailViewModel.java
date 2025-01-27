package com.example.myvideogames.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myvideogames.models.Game;

public class DetailViewModel extends ViewModel {
    private final MutableLiveData<Game> selectedGame = new MutableLiveData<>();

    public LiveData<Game> getSelectedGame() {
        return selectedGame;
    }

    public void setSelectedGame(Game game) {
        selectedGame.setValue(game);
    }
}
