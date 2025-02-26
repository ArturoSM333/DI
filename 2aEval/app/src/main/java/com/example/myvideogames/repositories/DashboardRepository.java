package com.example.myvideogames.repositories;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.example.myvideogames.models.Game;

import java.util.ArrayList;
import java.util.List;

// Repositorio encargado de la carga de videojuegos desde Firebase
public class DashboardRepository {
    private DatabaseReference mDatabase; // Referencia a la base de datos de Firebase

    // Constructor que inicializa la referencia a la base de datos
    public DashboardRepository() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    // Método para cargar la lista de videojuegos desde Firebase
    public void loadGames(OnDataLoadedListener listener) {
        // Accede a la colección "videojuegos" en Firebase
        mDatabase.child("videojuegos").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Game> games = new ArrayList<>(); // Lista donde se almacenarán los videojuegos
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Game game = snapshot.getValue(Game.class); // Convierte los datos en un objeto Game
                    if (game != null) {
                        games.add(game); // Agrega el videojuego a la lista si no es nulo
                    }
                }
                listener.onDataLoaded(games); // Notifica al listener que los datos han sido cargados
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                listener.onDataFailed(databaseError.toException()); // Notifica al listener si hay un error
            }
        });
    }

    // Interfaz que permite manejar los datos una vez cargados o si ocurre un error
    public interface OnDataLoadedListener {
        void onDataLoaded(List<Game> games); // Método llamado cuando los juegos se cargan con éxito
        void onDataFailed(Exception e); // Método llamado cuando hay un error en la carga de datos
    }
}
