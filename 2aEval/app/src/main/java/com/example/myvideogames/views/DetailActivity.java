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


public class DetailActivity extends AppCompatActivity {
    private DetailViewModel detailViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailViewModel = new ViewModelProvider(this).get(DetailViewModel.class);

        // UI elements
        ImageView imageView = findViewById(R.id.detailImageView);
        TextView titleTextView = findViewById(R.id.detailTitleTextView);
        TextView descriptionTextView = findViewById(R.id.detailDescriptionTextView);
        Button backbutton = findViewById(R.id.backButton);

        // Obtener los datos del Intent
        Game game = (Game) getIntent().getSerializableExtra("GAME_DATA");
        if (game != null) {
            detailViewModel.setSelectedGame(game);
        }


        findViewById(R.id.backButton).setOnClickListener(v -> {
            Intent intent = new Intent(DetailActivity.this, DashboardActivity.class);
            startActivity(intent);
            finish(); // Opcional: para evitar que el usuario regrese a DetailActivity
        });

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

    }
}
