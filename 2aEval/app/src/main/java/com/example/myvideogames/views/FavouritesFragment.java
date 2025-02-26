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
    private RecyclerView recyclerView; // RecyclerView para mostrar los juegos favoritos
    private GameAdapter gameAdapter; // Adapter para gestionar la visualización de los juegos
    private FavoritesManager favoritesManager; // Gestor de favoritos
    private List<Game> favoriteGames; // Lista de juegos favoritos

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflar el layout del fragmento
        View view = inflater.inflate(R.layout.fragment_favourites, container, false);

        // Inicializar RecyclerView y configurar su LayoutManager
        recyclerView = view.findViewById(R.id.favouritesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Inicializar el FavoritesManager y la lista de juegos favoritos
        favoritesManager = new FavoritesManager(requireActivity().getApplication());
        favoriteGames = new ArrayList<>();

        // Crear el adapter para el RecyclerView
        gameAdapter = new GameAdapter(
                getContext(),
                favoriteGames,
                // Listener para eliminar un juego de los favoritos
                game -> {
                    favoritesManager.removeFavorite(game); // Eliminar del gestor de favoritos
                    favoriteGames.remove(game); // Eliminar de la lista
                    gameAdapter.notifyDataSetChanged(); // Notificar al adapter para que actualice la vista
                    Toast.makeText(getContext(), "Game removed from favorites", Toast.LENGTH_SHORT).show();
                },
                // Listener para abrir el detalle de un juego al hacer clic
                game -> openDetailFragment(game)
        );

        // Establecer el adapter para el RecyclerView
        recyclerView.setAdapter(gameAdapter);

        // Cargar los juegos favoritos
        loadFavorites();

        return view;
    }

    // Método para cargar los juegos favoritos desde el FavoritesManager
    private void loadFavorites() {
        favoriteGames.clear(); // Limpiar la lista antes de cargar los nuevos elementos
        favoriteGames.addAll(favoritesManager.getFavorites()); // Añadir los juegos favoritos
        gameAdapter.notifyDataSetChanged(); // Notificar al adapter para que actualice la vista
    }

    // Método para abrir el fragmento de detalle del juego
    private void openDetailFragment(Game game) {
        // Crear un bundle para pasar los datos del juego al fragmento de detalle
        Bundle bundle = new Bundle();
        bundle.putSerializable("GAME_DATA", game);

        // Crear el fragmento de detalle y pasarle los datos
        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setArguments(bundle);

        // Realizar la transacción para reemplazar el fragmento actual por el fragmento de detalle
        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, detailFragment)
                .addToBackStack(null) // Agregar a la pila de retroceso
                .commit();
    }

    @Override
    public void onResume() {
        super.onResume();
        // Volver a cargar los favoritos cuando se vuelva a este fragmento
        loadFavorites();
    }
}
