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
    private Game game;
    private FavoritesManager favoritesManager;
    private Button favoriteButton;

    public DetailFragment() {
        // Constructor vacío requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        favoritesManager = new FavoritesManager(requireActivity().getApplication());

        if (getArguments() != null) {
            game = (Game) getArguments().getSerializable("GAME_DATA");
        }

        if (game == null) {
            requireActivity().getSupportFragmentManager().popBackStack();
            return;
        }

        TextView titleTextView = view.findViewById(R.id.detailTitleTextView);
        TextView descriptionTextView = view.findViewById(R.id.detailDescriptionTextView);
        ImageView imageView = view.findViewById(R.id.detailImageView);
        favoriteButton = view.findViewById(R.id.favoriteButton);
        Button backButton = view.findViewById(R.id.backButton);

        titleTextView.setText(game.getTitulo());
        descriptionTextView.setText(game.getDescripcion());

        Glide.with(requireContext())
                .load(game.getImagen())
                .into(imageView);

        updateFavoriteButton();

        favoriteButton.setOnClickListener(v -> {
            if (favoritesManager.isFavorite(game)) {
                favoritesManager.removeFavorite(game);
            } else {
                favoritesManager.addFavorite(game);
            }
            updateFavoriteButton();
        });

        backButton.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStack());
    }

    private void updateFavoriteButton() {
        if (favoritesManager.isFavorite(game)) {
            favoriteButton.setText("Eliminar de Favoritos");
        } else {
            favoriteButton.setText("Agregar a Favoritos");
        }
    }
}