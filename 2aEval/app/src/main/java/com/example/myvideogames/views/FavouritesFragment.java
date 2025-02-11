package com.example.myvideogames.views;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myvideogames.R;
import com.example.myvideogames.adapters.FavoriteAdapter;
import com.example.myvideogames.models.Game;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class FavouritesFragment extends Fragment {

    private RecyclerView recyclerView;
    private FavoriteAdapter favouritesAdapter;
    private List<Game> favouriteGames;

    public FavouritesFragment() {
        // Required empty public constructor
    }

    public static FavouritesFragment newInstance() {
        return new FavouritesFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favourites, container, false);

        recyclerView = rootView.findViewById(R.id.favouritesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize favourites list and adapter
        favouriteGames = new ArrayList<>();
        favouritesAdapter = new FavoriteAdapter(favouriteGames);
        recyclerView.setAdapter(favouritesAdapter);

        // Load favourite games (from SharedPreferences or database)
        loadFavouriteGames();

        return rootView;
    }

    private void loadFavouriteGames() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            db.collection("favorites")
                    .document(user.getUid())
                    .collection("userFavorites")
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            favouriteGames.clear();
                            for (DocumentSnapshot document : task.getResult()) {
                                Game game = document.toObject(Game.class);
                                favouriteGames.add(game);
                            }
                            favouritesAdapter.notifyDataSetChanged();
                        }
                    });
        }
    }

}
