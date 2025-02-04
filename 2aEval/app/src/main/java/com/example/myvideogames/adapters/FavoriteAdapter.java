package com.example.myvideogames.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myvideogames.R;
import com.example.myvideogames.models.Game;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
    private List<Game> favoriteGames;
    private Context context;

    public FavoriteAdapter(List<Game> favoriteGames, Context context) {
        this.favoriteGames = favoriteGames;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Game game = favoriteGames.get(position);
        holder.gameTitle.setText(game.getTitulo());
        Glide.with(context).load(game.getImagen()).into(holder.gameImage);
    }

    @Override
    public int getItemCount() {
        return favoriteGames.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView gameTitle;
        ImageView gameImage;

        public ViewHolder(View itemView) {
            super(itemView);
            gameTitle = itemView.findViewById(R.id.gameTitle);
            gameImage = itemView.findViewById(R.id.gameImage);
        }
    }
}
