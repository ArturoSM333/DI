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

    private RecyclerView recyclerView;
    private DashboardViewModel dashboardViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el layout del fragmento
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        // Configurar el RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Obtener el ViewModel
        dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);

        // Observar los juegos en el LiveData
        dashboardViewModel.getGamesLiveData().observe(getViewLifecycleOwner(), games -> {
            GameAdapter adapter = new GameAdapter(getActivity(), games, new GameAdapter.FavoriteActionListener() {
                @Override
                public void onFavoriteAction(Game game) {
                    // Aquí gestionas la acción de agregar a favoritos
                    addToFavorites(game);
                }
            });
            recyclerView.setAdapter(adapter);
        });

        // Cargar los juegos
        dashboardViewModel.loadGames();

        return view;
    }

    // Método para agregar a favoritos
    private void addToFavorites(Game game) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Usar el título del juego como el ID único
            db.collection("favorites")
                    .document(user.getUid()) // Usar el ID del usuario para separar los favoritos por usuario
                    .collection("userFavorites")
                    .document(game.getTitulo()) // Usar el título del juego como documento
                    .set(game)
                    .addOnSuccessListener(aVoid -> {
                        // Mostrar un mensaje o actualizar el UI
                        Toast.makeText(getContext(), "Game added to favorites", Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(e -> {
                        // Manejar el error si no se puede agregar el juego
                        Toast.makeText(getContext(), "Error adding game to favorites", Toast.LENGTH_SHORT).show();
                    });
        }
    }

}
