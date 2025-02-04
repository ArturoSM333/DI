package com.example.myvideogames.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myvideogames.R;
import com.example.myvideogames.adapters.GameAdapter;
import com.example.myvideogames.models.Game;
import com.example.myvideogames.viewmodels.DashboardViewModel;
import com.google.firebase.auth.FirebaseAuth;
import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {
    private DashboardViewModel dashboardViewModel;
    private RecyclerView recyclerView;
    private GameAdapter gameAdapter;
    private List<Game> gameList = new ArrayList<>();
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAuth = FirebaseAuth.getInstance();

        // Botón para cerrar sesión
        Button logoutButton = findViewById(R.id.logoutButton);

        // Botón para ver favoritos
        Button favoritesButton = findViewById(R.id.favoritesButton);
        favoritesButton.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, FavouritesActivity.class);
            startActivity(intent);
        });


        // Configuración del adaptador
        gameAdapter = new GameAdapter(this, gameList, game -> {
            Intent intent = new Intent(DashboardActivity.this, DetailActivity.class);
            intent.putExtra("GAME_DATA", game);
            startActivity(intent);
        });
        recyclerView.setAdapter(gameAdapter);

        // Observar los juegos
        dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
        dashboardViewModel.getGamesLiveData().observe(this, games -> {
            if (games != null) {
                gameList.clear();
                gameList.addAll(games);
                gameAdapter.notifyDataSetChanged();
            }
        });


        dashboardViewModel.loadGames();

        // Evento para cerrar sesión
        logoutButton.setOnClickListener(v -> {
            mAuth.signOut();
            startActivity(new Intent(DashboardActivity.this, LoginActivity.class));
            finish();
        });
    }
}
