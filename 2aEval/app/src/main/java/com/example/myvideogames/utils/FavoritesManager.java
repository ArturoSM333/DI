package com.example.myvideogames.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import com.example.myvideogames.models.Game;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FavoritesManager {

    private static final String PREFS_NAME = "FavoritesPrefs";
    private static final String FAVORITES_KEY = "favorites";
    private SharedPreferences sharedPreferences;

    public FavoritesManager(Application application) {
        sharedPreferences = application.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    // Método para agregar un juego a favoritos
    public void addFavorite(Game game) {
        List<Game> favoriteGames = getFavorites();

        // Verificar si el juego ya está en la lista
        if (!favoriteGames.contains(game)) {
            favoriteGames.add(game); // Agregar el juego si no está en la lista
            saveFavorites(favoriteGames); // Guardar la lista actualizada
        }
    }

    // Método para eliminar un juego de favoritos
    public void removeFavorite(Game game) {
        List<Game> favoriteGames = getFavorites();
        if (favoriteGames.contains(game)) {
            favoriteGames.remove(game); // Eliminar el juego de la lista
            saveFavorites(favoriteGames); // Guardar la lista actualizada
        }
    }

    // Método para verificar si un juego está en favoritos
    public boolean isFavorite(Game game) {
        List<Game> favoriteGames = getFavorites();
        return favoriteGames.contains(game);
    }

    // Método para obtener la lista de juegos favoritos
    public List<Game> getFavorites() {
        // Recuperar la lista de favoritos desde SharedPreferences
        String json = sharedPreferences.getString(FAVORITES_KEY, "[]");
        return new Gson().fromJson(json, new TypeToken<List<Game>>(){}.getType());
    }

    // Método para guardar la lista de juegos favoritos
    private void saveFavorites(List<Game> favoriteGames) {
        // Convertir la lista a JSON y guardarla en SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String json = new Gson().toJson(favoriteGames);
        editor.putString(FAVORITES_KEY, json);
        editor.apply(); // Aplicar cambios
    }
}
