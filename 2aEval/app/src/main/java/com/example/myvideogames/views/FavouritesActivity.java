package com.example.myvideogames.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myvideogames.R;
import com.example.myvideogames.adapters.GameAdapter;
import com.example.myvideogames.models.Game;
import com.example.myvideogames.viewmodels.FavoritesViewModel;

import java.util.ArrayList;
import java.util.List;

public class FavouritesActivity extends AppCompatActivity {
    private FavoritesViewModel favouritesViewModel;
    private RecyclerView recyclerView;
    private GameAdapter gameAdapter;
    private List<Game> favoriteGames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        recyclerView = findViewById(R.id.favouritesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Button backButton = findViewById(R.id.backButton);
        favoriteGames = new ArrayList<>();  // Inicializa la lista antes de usarla
        gameAdapter = new GameAdapter(this, favoriteGames, game -> {
            if (game != null) {
                Intent intent = new Intent(FavouritesActivity.this, DetailActivity.class);
                intent.putExtra("GAME_DATA", game);  // Asegúrate de que game no es null
                startActivity(intent);
            } else {
                Log.e("FavouritesActivity", "El juego es nulo, no se puede pasar a DetailActivity");
            }
        });

        recyclerView.setAdapter(gameAdapter);

        favouritesViewModel = new ViewModelProvider(this).get(FavoritesViewModel.class);

        // Observar los juegos favoritos
        favouritesViewModel.getFavoriteGames().observe(this, games -> {
            if (games != null) {
                favoriteGames.clear();  // Borrar la lista anterior si es necesario
                favoriteGames.addAll(games);  // Añadir los nuevos juegos
                gameAdapter.notifyDataSetChanged();  // Notificar al adaptador de que los datos han cambiado
            }
        });

        // Configurar botón de regreso
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(FavouritesActivity.this, DashboardActivity.class);
            startActivity(intent);
            finish();
        });

        // Cargar los juegos favoritos
        favouritesViewModel.loadFavorites();
    }
}
