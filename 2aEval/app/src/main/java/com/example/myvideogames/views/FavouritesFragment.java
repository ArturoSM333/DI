package com.example.myvideogames.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myvideogames.R;
import com.example.myvideogames.adapters.GameAdapter;
import com.example.myvideogames.models.Game;
import com.example.myvideogames.utils.FavoritesManager;

import java.util.ArrayList;
import java.util.List;

public class FavouritesFragment extends Fragment {
    private RecyclerView recyclerView;
    private GameAdapter gameAdapter;
    private FavoritesManager favoritesManager;
    private List<Game> favoriteGames;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourites, container, false);

        recyclerView = view.findViewById(R.id.favouritesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        favoritesManager = new FavoritesManager(requireActivity().getApplication());
        favoriteGames = new ArrayList<>();

        // Crear el adapter con ambos listeners
        gameAdapter = new GameAdapter(
                getContext(),
                favoriteGames,
                // Listener para favoritos
                game -> {
                    favoritesManager.removeFavorite(game);
                    favoriteGames.remove(game);
                    gameAdapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), "Game removed from favorites", Toast.LENGTH_SHORT).show();
                },
                // Listener para click en el item
                game -> openDetailFragment(game)
        );

        recyclerView.setAdapter(gameAdapter);
        loadFavorites();

        return view;
    }

    private void loadFavorites() {
        favoriteGames.clear();
        favoriteGames.addAll(favoritesManager.getFavorites());
        gameAdapter.notifyDataSetChanged();
    }

    private void openDetailFragment(Game game) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("GAME_DATA", game);

        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setArguments(bundle);

        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, detailFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadFavorites();
    }
}