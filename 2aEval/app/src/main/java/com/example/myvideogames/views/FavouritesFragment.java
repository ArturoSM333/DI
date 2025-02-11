package com.example.myvideogames.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myvideogames.R;
import com.example.myvideogames.adapters.GameAdapter;
import com.example.myvideogames.models.Game;
import com.example.myvideogames.viewmodels.FavoritesViewModel;

import java.util.ArrayList;
import java.util.List;

public class FavouritesFragment extends Fragment {

    private FavoritesViewModel favouritesViewModel;
    private RecyclerView recyclerView;
    private GameAdapter gameAdapter;
    private List<Game> favoriteGames;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Infla el layout del fragmento
        View view = inflater.inflate(R.layout.fragment_favourites, container, false);

        // Inicializa las vistas
        recyclerView = view.findViewById(R.id.favouritesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        favoriteGames = new ArrayList<>();
        gameAdapter = new GameAdapter(getContext(), favoriteGames, game -> {
            if (game != null) {
                Intent intent = new Intent(getContext(), DetailFragment.class);
                intent.putExtra("GAME_DATA", game);
                startActivity(intent);
            } else {
                Log.e("FavouritesFragment", "El juego es nulo, no se puede pasar a DetailActivity");
            }
        });
        recyclerView.setAdapter(gameAdapter);

        // Inicializa el ViewModel
        favouritesViewModel = new ViewModelProvider(this).get(FavoritesViewModel.class);

        // Observar los juegos favoritos
        favouritesViewModel.getFavoriteGames().observe(getViewLifecycleOwner(), games -> {
            if (games != null) {
                favoriteGames.clear();
                favoriteGames.addAll(games);
                gameAdapter.notifyDataSetChanged();
            }
        });

        // Cargar los juegos favoritos
        favouritesViewModel.loadFavorites();

        return view;
    }
}
