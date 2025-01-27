package com.example.myvideogames.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.myvideogames.R;
import com.example.myvideogames.models.Game;
import com.example.myvideogames.viewmodels.DetailViewModel;
import com.google.firebase.auth.FirebaseAuth;

public class DetailActivity extends AppCompatActivity {
    private DetailViewModel detailViewModel;
    private FirebaseAuth mAuth;  // Declaramos FirebaseAuth para cerrar sesión

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailViewModel = new ViewModelProvider(this).get(DetailViewModel.class);
        mAuth = FirebaseAuth.getInstance(); // Inicializamos FirebaseAuth

        // UI elements
        ImageView imageView = findViewById(R.id.detailImageView);
        TextView titleTextView = findViewById(R.id.detailTitleTextView);
        TextView descriptionTextView = findViewById(R.id.detailDescriptionTextView);
        Button logoutButton = findViewById(R.id.logoutButton); // Referencia al botón logout

        // Obtener los datos del Intent
        Game game = (Game) getIntent().getSerializableExtra("GAME_DATA");
        if (game != null) {
            detailViewModel.setSelectedGame(game);
        }

        // Observar el ViewModel
        detailViewModel.getSelectedGame().observe(this, selectedGame -> {
            if (selectedGame != null) {
                titleTextView.setText(selectedGame.getTitulo());
                descriptionTextView.setText(selectedGame.getDescripcion());

                // Cargar imagen usando Glide
                Glide.with(this)
                        .load(selectedGame.getImagen())
                        .into(imageView);
            }
        });

        // Configuración del botón logout
        logoutButton.setOnClickListener(v -> {
            mAuth.signOut(); // Cerrar sesión
            Intent intent = new Intent(DetailActivity.this, LoginActivity.class); // Redirigir al login
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK); // Limpiar la pila de actividades
            startActivity(intent);
            finish(); // Finalizar DetailActivity
        });
    }
}
