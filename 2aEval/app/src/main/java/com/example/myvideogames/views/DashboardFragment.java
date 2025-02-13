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
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);

        dashboardViewModel.getGamesLiveData().observe(getViewLifecycleOwner(), games -> {
            GameAdapter adapter = new GameAdapter(
                    getActivity(),
                    games,
                    // Listener para favoritos
                    new GameAdapter.FavoriteActionListener() {
                        @Override
                        public void onFavoriteAction(Game game) {
                            addToFavorites(game);
                        }
                    },
                    // Listener para click en el item
                    new GameAdapter.OnGameClickListener() {
                        @Override
                        public void onGameClick(Game game) {
                            openDetailFragment(game);
                        }
                    }
            );
            recyclerView.setAdapter(adapter);
        });

        dashboardViewModel.loadGames();

        return view;
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

    private void addToFavorites(Game game) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            db.collection("favorites")
                    .document(user.getUid())
                    .collection("userFavorites")
                    .document(game.getTitulo())
                    .set(game)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(getContext(), "Game added to favorites", Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(getContext(), "Error adding game to favorites", Toast.LENGTH_SHORT).show();
                    });
        }
    }
}