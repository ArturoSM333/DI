package com.example.myvideogames.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myvideogames.R;
import com.example.myvideogames.adapters.GameAdapter;
import com.example.myvideogames.models.Game;
import com.example.myvideogames.viewmodels.DashboardViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class DashboardFragment extends Fragment {

    private RecyclerView recyclerView; // RecyclerView para mostrar los juegos
    private DashboardViewModel dashboardViewModel; // ViewModel para manejar los datos de juegos

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el layout del fragmento
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        // Inicializar RecyclerView y establecer el LayoutManager
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Obtener la instancia del ViewModel
        dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);

        // Observar los cambios en la lista de juegos en el ViewModel
        dashboardViewModel.getGamesLiveData().observe(getViewLifecycleOwner(), games -> {
            // Crear un adaptador y asignarlo al RecyclerView
            GameAdapter adapter = new GameAdapter(
                    getActivity(),
                    games,
                    // Listener para la acción de agregar a favoritos
                    new GameAdapter.FavoriteActionListener() {
                        @Override
                        public void onFavoriteAction(Game game) {
                            addToFavorites(game); // Agregar a favoritos cuando el usuario interactúa
                        }
                    },
                    // Listener para hacer click en un juego
                    new GameAdapter.OnGameClickListener() {
                        @Override
                        public void onGameClick(Game game) {
                            openDetailFragment(game); // Abrir el fragmento de detalle del juego
                        }
                    }
            );
            recyclerView.setAdapter(adapter); // Establecer el adaptador
        });

        // Cargar los juegos desde el ViewModel
        dashboardViewModel.loadGames();

        return view; // Retornar la vista inflada
    }

    // Método para abrir el fragmento de detalle del juego
    private void openDetailFragment(Game game) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("GAME_DATA", game); // Pasar los datos del juego al fragmento de detalle

        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setArguments(bundle); // Configurar los argumentos del fragmento

        // Reemplazar el fragmento actual por el fragmento de detalle
        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, detailFragment) // Reemplazar el fragmento
                .addToBackStack(null) // Añadir a la pila de retroceso
                .commit(); // Realizar la transacción
    }

    // Método para agregar un juego a los favoritos en Firebase
    private void addToFavorites(Game game) {
        FirebaseFirestore db = FirebaseFirestore.getInstance(); // Obtener instancia de Firestore
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); // Obtener el usuario autenticado

        if (user != null) {
            // Añadir el juego a la colección "favorites" del usuario
            db.collection("favorites")
                    .document(user.getUid()) // Usar el UID del usuario
                    .collection("userFavorites")
                    .document(game.getTitulo()) // Usar el título del juego como documento
                    .set(game) // Guardar el objeto del juego
                    .addOnSuccessListener(aVoid -> {
                        // Mostrar un mensaje cuando el juego se agrega a favoritos exitosamente
                        Toast.makeText(getContext(), "Game added to favorites", Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(e -> {
                        // Mostrar un mensaje en caso de error
                        Toast.makeText(getContext(), "Error adding game to favorites", Toast.LENGTH_SHORT).show();
                    });
        }
    }
}
