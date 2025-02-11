package com.example.myvideogames.adapters;

import android.content.Context;
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

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameViewHolder> {
    private Context context;
    private List<Game> gameList;
    private FavoriteActionListener favoriteActionListener;

    public GameAdapter(Context context, List<Game> gameList, FavoriteActionListener favoriteActionListener) {
        this.context = context;
        this.gameList = gameList;
        this.favoriteActionListener = favoriteActionListener;
    }

    @Override
    public GameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_game, parent, false);
        return new GameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GameViewHolder holder, int position) {
        Game game = gameList.get(position);
        holder.titleTextView.setText(game.getTitulo());

        Glide.with(context)
                .load(game.getImagen())
                .into(holder.imageView);

        holder.itemView.setOnClickListener(v -> {
            if (favoriteActionListener != null) {
                favoriteActionListener.onFavoriteAction(game);
            }
        });
    }

    @Override
    public int getItemCount() {
        return gameList.size();
    }

    public static class GameViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleTextView;

        public GameViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.gameImageView);
            titleTextView = itemView.findViewById(R.id.gameTitleTextView);
        }
    }

    public interface FavoriteActionListener {
        void onFavoriteAction(Game game);
    }
}