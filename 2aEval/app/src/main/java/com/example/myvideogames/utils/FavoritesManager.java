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

    // Nombre del archivo de SharedPreferences donde se almacenarán los favoritos
    private static final String PREFS_NAME = "FavoritesPrefs";
    // Clave bajo la cual se guardará la lista de favoritos
    private static final String FAVORITES_KEY = "favorites";
    // Instancia de SharedPreferences para guardar y recuperar los datos
    private SharedPreferences sharedPreferences;

    // Constructor que recibe el contexto de la aplicación
    public FavoritesManager(Application application) {
        // Inicialización de SharedPreferences con el modo privado
        sharedPreferences = application.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    // Método para agregar un juego a la lista de favoritos
    public void addFavorite(Game game) {
        // Obtener la lista actual de juegos favoritos
        List<Game> favoriteGames = getFavorites();

        // Verificar si el juego ya está en la lista de favoritos
        if (!favoriteGames.contains(game)) {
            favoriteGames.add(game); // Agregar el juego si no está en la lista
            saveFavorites(favoriteGames); // Guardar la lista actualizada en SharedPreferences
        }
    }

    // Método para eliminar un juego de la lista de favoritos
    public void removeFavorite(Game game) {
        // Obtener la lista actual de juegos favoritos
        List<Game> favoriteGames = getFavorites();

        // Verificar si el juego está en la lista antes de intentar eliminarlo
        if (favoriteGames.contains(game)) {
            favoriteGames.remove(game); // Eliminar el juego de la lista
            saveFavorites(favoriteGames); // Guardar la lista actualizada en SharedPreferences
        }
    }

    // Método para verificar si un juego está en la lista de favoritos
    public boolean isFavorite(Game game) {
        // Obtener la lista de favoritos y verificar si el juego está presente
        List<Game> favoriteGames = getFavorites();
        return favoriteGames.contains(game); // Devuelve true si está en favoritos, false si no
    }

    // Método para obtener la lista de juegos favoritos desde SharedPreferences
    public List<Game> getFavorites() {
        // Recuperar el JSON almacenado en SharedPreferences bajo la clave FAVORITES_KEY
        String json = sharedPreferences.getString(FAVORITES_KEY, "[]");
        // Convertir el JSON a una lista de objetos Game usando Gson
        return new Gson().fromJson(json, new TypeToken<List<Game>>(){}.getType());
    }

    // Método privado para guardar la lista de juegos favoritos en SharedPreferences
    private void saveFavorites(List<Game> favoriteGames) {
        // Convertir la lista de juegos favoritos a un string JSON
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String json = new Gson().toJson(favoriteGames);
        // Guardar el JSON en SharedPreferences bajo la clave FAVORITES_KEY
        editor.putString(FAVORITES_KEY, json);
        // Aplicar los cambios
        editor.apply();
    }
}
