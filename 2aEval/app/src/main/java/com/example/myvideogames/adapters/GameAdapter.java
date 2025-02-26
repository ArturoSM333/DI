package com.example.myvideogames.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.myvideogames.R;
import com.example.myvideogames.models.Game;

import java.util.List;

// Adaptador para mostrar una lista de juegos en un RecyclerView
public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameViewHolder> {
    private Context context; // Contexto de la aplicación
    private List<Game> gameList; // Lista de juegos a mostrar
    private FavoriteActionListener favoriteActionListener; // Listener para acciones de favoritos
    private OnGameClickListener gameClickListener; // Listener para clics en juegos

    // Constructor que inicializa el adaptador con la lista de juegos y los listeners
    public GameAdapter(Context context, List<Game> gameList,
                       FavoriteActionListener favoriteActionListener,
                       OnGameClickListener gameClickListener) {
        this.context = context;
        this.gameList = gameList;
        this.favoriteActionListener = favoriteActionListener;
        this.gameClickListener = gameClickListener;
    }

    @Override
    public GameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Infla el diseño del item desde el XML 'item_game.xml'
        View view = LayoutInflater.from(context).inflate(R.layout.item_game, parent, false);
        return new GameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GameViewHolder holder, int position) {
        // Obtiene el juego en la posición actual
        Game game = gameList.get(position);

        // Establece el título del juego en el TextView
        holder.titleTextView.setText(game.getTitulo());

        // Carga la imagen del juego usando Glide
        Glide.with(context)
                .load(game.getImagen()) // URL de la imagen
                .into(holder.imageView); // Establece la imagen en el ImageView

        // Click en el elemento para abrir el DetailFragment
        holder.itemView.setOnClickListener(v -> {
            if (gameClickListener != null) {
                gameClickListener.onGameClick(game);
            }
        });

        // Click en el botón de favoritos para agregar o quitar de favoritos
        holder.favoriteButton.setOnClickListener(v -> {
            if (favoriteActionListener != null) {
                favoriteActionListener.onFavoriteAction(game);
            }
        });
    }

    @Override
    public int getItemCount() {
        return gameList.size(); // Retorna el número total de juegos en la lista
    }

    // Clase interna que representa la vista de cada elemento de la lista
    public static class GameViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView; // ImageView para la imagen del juego
        TextView titleTextView; // TextView para el título del juego
        ImageButton favoriteButton; // Botón para marcar como favorito

        // Constructor que inicializa las vistas del item
        public GameViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.gameImageView);
            titleTextView = itemView.findViewById(R.id.gameTitleTextView);
            favoriteButton = itemView.findViewById(R.id.favoriteButton);
        }
    }

    // Interfaz para manejar acciones de favoritos
    public interface FavoriteActionListener {
        void onFavoriteAction(Game game);
    }

    // Interfaz para manejar clics en los juegos
    public interface OnGameClickListener {
        void onGameClick(Game game);
    }
}
