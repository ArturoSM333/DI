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

    public void addFavorite(Game game) {
        List<Game> favoriteGames = getFavorites();
        if (!favoriteGames.contains(game)) {
            favoriteGames.add(game);
            saveFavorites(favoriteGames);
        }
    }

    public void removeFavorite(Game game) {
        List<Game> favoriteGames = getFavorites();
        if (favoriteGames.contains(game)) {
            favoriteGames.remove(game);
            saveFavorites(favoriteGames);
        }
    }

    public boolean isFavorite(Game game) {
        List<Game> favoriteGames = getFavorites();
        return favoriteGames.contains(game);
    }

    public List<Game> getFavorites() {
        String json = sharedPreferences.getString(FAVORITES_KEY, "[]");
        return new Gson().fromJson(json, new TypeToken<List<Game>>(){}.getType());
    }

    private void saveFavorites(List<Game> favoriteGames) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String json = new Gson().toJson(favoriteGames);
        editor.putString(FAVORITES_KEY, json);
        editor.apply();
    }

    // Método agregado para limpiar todos los favoritos
    public void clearFavorites() {
        saveFavorites(new ArrayList<>());
    }
}
