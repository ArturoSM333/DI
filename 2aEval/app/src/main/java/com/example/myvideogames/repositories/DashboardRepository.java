package com.example.myvideogames.repositories;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.example.myvideogames.models.Game;

import java.util.ArrayList;
import java.util.List;

public class DashboardRepository {

    private DatabaseReference mDatabase;

    public DashboardRepository() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void loadGames(OnDataLoadedListener listener) {
        mDatabase.child("videojuegos").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Game> games = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Game game = snapshot.getValue(Game.class);
                    if (game != null) {
                        games.add(game);
                    }
                }
                listener.onDataLoaded(games);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                listener.onDataFailed(databaseError.toException());
            }
        });
    }

    public interface OnDataLoadedListener {
        void onDataLoaded(List<Game> games);
        void onDataFailed(Exception e);
    }
}
