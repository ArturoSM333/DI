package com.example.myvideogames.viewmodels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.myvideogames.models.Game;
import com.example.myvideogames.utils.FavoritesManager;
import java.util.List;

public class FavoritesViewModel extends AndroidViewModel {
    private FavoritesManager favoritesManager;
    private final MutableLiveData<List<Game>> favoriteGamesLiveData;

    public FavoritesViewModel(Application application) {
        super(application);
        favoritesManager = new FavoritesManager(application);
        favoriteGamesLiveData = new MutableLiveData<>();
        loadFavorites();
    }

    // Cargar la lista de juegos favoritos
    public void loadFavorites() {
        List<Game> favoriteGames = favoritesManager.getFavorites();
        favoriteGamesLiveData.setValue(favoriteGames);
    }

    // Obtener la lista de juegos favoritos
    public LiveData<List<Game>> getFavoriteGames() {
        return favoriteGamesLiveData;
    }

    // Agregar un juego a los favoritos
    public void addFavorite(Game game) {
        favoritesManager.addFavorite(game);
        loadFavorites();  // Actualizar la lista de favoritos
    }

    // Eliminar un juego de los favoritos
    public void removeFavorite(Game game) {
        favoritesManager.removeFavorite(game);
        loadFavorites();  // Actualizar la lista de favoritos
    }

    // Verificar si un juego está en favoritos
    public boolean isFavorite(Game game) {
        return favoritesManager.isFavorite(game);
    }
}
