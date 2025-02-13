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

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {

    private List<Game> favoriteGames;

    // Constructor del adaptador
    public FavoriteAdapter(List<Game> favoriteGames) {
        this.favoriteGames = favoriteGames;
    }
    
    @Override
    public FavoriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Cambia esto para que cargue 'item_favorite.xml' en lugar de 'item_favorite_game.xml'
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite, parent, false);
        return new FavoriteViewHolder(view);
    }


    @Override
    public void onBindViewHolder(FavoriteViewHolder holder, int position) {
        Game game = favoriteGames.get(position);
        holder.titleTextView.setText(game.getTitulo());
        holder.descriptionTextView.setText(game.getDescripcion());

        // Si tienes una URL para la imagen, puedes usar Glide para cargarla
        Glide.with(holder.itemView.getContext())
                .load(game.getImagen()) // Aquí cargas la imagen usando Glide o Picasso
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return favoriteGames.size();
    }

    public static class FavoriteViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView descriptionTextView;
        ImageView imageView;

        public FavoriteViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.gameTitleTextView);
            descriptionTextView = itemView.findViewById(R.id.gameDescriptionTextView);
            imageView = itemView.findViewById(R.id.gameImageView);
        }
    }
}
