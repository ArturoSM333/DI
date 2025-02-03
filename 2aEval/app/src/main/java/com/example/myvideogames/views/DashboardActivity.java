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
import com.google.firebase.auth.FirebaseAuth;

public class DashboardActivity extends AppCompatActivity {
    private DashboardViewModel dashboardViewModel;
    private RecyclerView recyclerView;
    private GameAdapter gameAdapter;
    private List<Game> gameList = new ArrayList<>();
    private FirebaseAuth mAuth;  // Declaramos FirebaseAuth para cerrar sesión

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAuth = FirebaseAuth.getInstance(); // Inicializamos FirebaseAuth

        Button logoutButton = findViewById(R.id.logoutButton); // Referencia al botón logout
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
        // Configuración del botón logout
        logoutButton.setOnClickListener(v -> {
            mAuth.signOut(); // Cerrar sesión
            Intent intent = new Intent(DashboardActivity.this, LoginActivity.class); // Redirigir al login
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK); // Limpiar la pila de actividades
            startActivity(intent);
            finish(); // Finalizar DetailActivity
        });
    }
}
