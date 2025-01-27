package com.example.myvideogames.views;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myvideogames.R;
import com.example.myvideogames.adapters.GameAdapter;
import com.example.myvideogames.models.Game;
import com.example.myvideogames.viewmodels.DashboardViewModel;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {
    private DashboardViewModel dashboardViewModel;
    private RecyclerView recyclerView;
    private GameAdapter gameAdapter;
    private List<Game> gameList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        gameAdapter = new GameAdapter(this, gameList);
        recyclerView.setAdapter(gameAdapter);

        dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);

        // Observar los datos de los juegos
        dashboardViewModel.getGamesLiveData().observe(this, games -> {
            if (games != null) {
                gameList.clear();
                gameList.addAll(games); // Agregar los juegos a la lista
                gameAdapter.notifyDataSetChanged(); // Notificar al adaptador de que hay nuevos datos
            }
        });

        // Cargar datos desde el repositorio
        dashboardViewModel.loadGames();
    }
}
