package com.example.myvideogames.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myvideogames.R;
import com.example.myvideogames.models.Game;

import java.util.List;

// Adaptador para la lista de juegos favoritos en un RecyclerView
public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {

    private List<Game> favoriteGames; // Lista de juegos favoritos

    // Constructor del adaptador que recibe la lista de juegos favoritos
    public FavoriteAdapter(List<Game> favoriteGames) {
        this.favoriteGames = favoriteGames;
    }

    @Override
    public FavoriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Infla el diseño del item de la lista desde el archivo XML 'item_favorite.xml'
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite, parent, false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FavoriteViewHolder holder, int position) {
        // Obtiene el juego en la posición actual de la lista
        Game game = favoriteGames.get(position);

        // Asigna los datos del juego a los elementos de la vista
        holder.titleTextView.setText(game.getTitulo()); // Establece el título del juego
        holder.descriptionTextView.setText(game.getDescripcion()); // Establece la descripción del juego

        // Carga la imagen del juego desde una URL usando la librería Glide
        Glide.with(holder.itemView.getContext())
                .load(game.getImagen()) // URL de la imagen
                .into(holder.imageView); // Establece la imagen en el ImageView
    }

    @Override
    public int getItemCount() {
        return favoriteGames.size(); // Retorna el número total de juegos en la lista
    }

    // Clase interna que representa la vista de cada elemento de la lista
    public static class FavoriteViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView; // TextView para el título del juego
        TextView descriptionTextView; // TextView para la descripción del juego
        ImageView imageView; // ImageView para la imagen del juego

        // Constructor que inicializa las vistas del item
        public FavoriteViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.gameTitleTextView);
            descriptionTextView = itemView.findViewById(R.id.gameDescriptionTextView);
            imageView = itemView.findViewById(R.id.gameImageView);
        }
    }
}
