package com.example.myvideogames.viewmodels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.myvideogames.models.Game;
import com.example.myvideogames.utils.FavoritesManager;
import java.util.List;

public class FavoritesViewModel extends AndroidViewModel {
    // Instancia del gestor de favoritos que maneja la lista persistente
    private FavoritesManager favoritesManager;

    // LiveData para almacenar la lista de juegos favoritos
    private final MutableLiveData<List<Game>> favoriteGamesLiveData;

    // Constructor que recibe el contexto de la aplicación
    public FavoritesViewModel(Application application) {
        super(application);
        // Inicialización del gestor de favoritos
        favoritesManager = new FavoritesManager(application);

        // Inicialización del LiveData para la lista de juegos favoritos
        favoriteGamesLiveData = new MutableLiveData<>();

        // Cargar los favoritos al inicializar el ViewModel
        loadFavorites();
    }

    // Método para cargar la lista de juegos favoritos desde el gestor
    public void loadFavorites() {
        // Obtener la lista de juegos favoritos
        List<Game> favoriteGames = favoritesManager.getFavorites();

        // Actualizar el LiveData con la lista de juegos favoritos
        favoriteGamesLiveData.setValue(favoriteGames);
    }

    // Método para obtener la lista de juegos favoritos (LiveData)
    public LiveData<List<Game>> getFavoriteGames() {
        return favoriteGamesLiveData;
    }

    // Método para agregar un juego a los favoritos
    public void addFavorite(Game game) {
        // Agregar el juego a los favoritos
        favoritesManager.addFavorite(game);

        // Volver a cargar la lista de favoritos para actualizarla
        loadFavorites();
    }

    // Método para eliminar un juego de los favoritos
    public void removeFavorite(Game game) {
        // Eliminar el juego de los favoritos
        favoritesManager.removeFavorite(game);

        // Volver a cargar la lista de favoritos para actualizarla
        loadFavorites();
    }

    // Método para verificar si un juego está en los favoritos
    public boolean isFavorite(Game game) {
        // Verificar si el juego está en la lista de favoritos
        return favoritesManager.isFavorite(game);
    }
}
