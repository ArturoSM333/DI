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
import com.example.myvideogames.utils.FavoritesManager;
import com.example.myvideogames.viewmodels.FavoritesViewModel;

public class DetailActivity extends AppCompatActivity {
    private Game game;
    private FavoritesManager favoritesManager;
    private Button favoriteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        favoritesManager = new FavoritesManager(getApplication());

        // Obtener los datos del juego desde el Intent
        game = (Game) getIntent().getSerializableExtra("GAME_DATA");
        if (game == null) {
            finish();
            return;
        }

        // Inicializar las vistas
        TextView titleTextView = findViewById(R.id.detailTitleTextView);
        TextView descriptionTextView = findViewById(R.id.detailDescriptionTextView);
        ImageView imageView = findViewById(R.id.detailImageView); // Asegúrate de que tengas esta vista en el XML

        // Configurar título
        titleTextView.setText(game.getTitulo());

        // Configurar descripción
        descriptionTextView.setText(game.getDescripcion());

        // Cargar la imagen con Glide
        Glide.with(this)
                .load(game.getImagen()) // Aquí deberías pasar la URL de la imagen
                .into(imageView);

        // Inicializar el botón de favoritos
        favoriteButton = findViewById(R.id.favoriteButton);
        Button backButton = findViewById(R.id.backButton);
        // Verificar si el juego está en favoritos y actualizar el texto del botón
        if (favoritesManager.isFavorite(game)) {
            favoriteButton.setText("Eliminar de Favoritos");
        } else {
            favoriteButton.setText("Agregar a Favoritos");
        }

        // Configurar acción del botón de favoritos
        favoriteButton.setOnClickListener(v -> {
            if (favoritesManager.isFavorite(game)) {
                favoritesManager.removeFavorite(game);
                favoriteButton.setText("Agregar a Favoritos");
            } else {
                favoritesManager.addFavorite(game);
                favoriteButton.setText("Eliminar de Favoritos");
            }
        });

        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(DetailActivity.this, DashboardActivity.class);
            startActivity(intent);
            finish();
        });
    }
}