package com.example.myvideogames.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.example.myvideogames.R;
import com.example.myvideogames.models.Game;
import com.example.myvideogames.utils.FavoritesManager;

public class DetailFragment extends Fragment {
    private Game game; // Objeto para almacenar los datos del juego
    private FavoritesManager favoritesManager; // Gestor de favoritos
    private Button favoriteButton; // Botón para agregar o quitar de favoritos

    public DetailFragment() {
        // Constructor vacío requerido por el sistema de fragmentos
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflar el layout del fragmento
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Inicializar el objeto FavoritesManager
        favoritesManager = new FavoritesManager(requireActivity().getApplication());

        // Obtener los datos del juego desde los argumentos del fragmento
        if (getArguments() != null) {
            game = (Game) getArguments().getSerializable("GAME_DATA");
        }

        // Si no se recibió un juego, regresar al fragmento anterior
        if (game == null) {
            requireActivity().getSupportFragmentManager().popBackStack();
            return;
        }

        // Inicializar los elementos de la UI
        TextView titleTextView = view.findViewById(R.id.detailTitleTextView);
        TextView descriptionTextView = view.findViewById(R.id.detailDescriptionTextView);
        ImageView imageView = view.findViewById(R.id.detailImageView);
        favoriteButton = view.findViewById(R.id.favoriteButton);
        Button backButton = view.findViewById(R.id.backButton);

        // Configurar los textos y la imagen del juego
        titleTextView.setText(game.getTitulo());
        descriptionTextView.setText(game.getDescripcion());

        // Cargar la imagen del juego usando Glide
        Glide.with(requireContext())
                .load(game.getImagen()) // Cargar la URL de la imagen
                .into(imageView); // Establecer la imagen en el ImageView

        // Actualizar el estado del botón de favoritos
        updateFavoriteButton();

        // Configurar el comportamiento del botón de favoritos
        favoriteButton.setOnClickListener(v -> {
            if (favoritesManager.isFavorite(game)) {
                // Si el juego ya está en favoritos, eliminarlo
                favoritesManager.removeFavorite(game);
            } else {
                // Si el juego no está en favoritos, agregarlo
                favoritesManager.addFavorite(game);
            }
            // Actualizar el estado del botón después de la acción
            updateFavoriteButton();
        });

        // Configurar el comportamiento del botón de regreso
        backButton.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStack());
    }

    // Método para actualizar el texto del botón de favoritos
    private void updateFavoriteButton() {
        // Si el juego está en favoritos, mostrar "Eliminar de Favoritos", de lo contrario "Agregar a Favoritos"
        if (favoritesManager.isFavorite(game)) {
            favoriteButton.setText("Eliminar de Favoritos");
        } else {
            favoriteButton.setText("Agregar a Favoritos");
        }
    }
}
