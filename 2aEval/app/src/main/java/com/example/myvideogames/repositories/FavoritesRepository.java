package com.example.myvideogames.repositories;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FieldValue;
import com.google.android.gms.tasks.Task;

// Repositorio encargado de manejar la funcionalidad de favoritos en Firestore
public class FavoritesRepository {
    private FirebaseFirestore db = FirebaseFirestore.getInstance(); // Instancia de la base de datos Firestore

    /**
     * Agrega un videojuego a la lista de favoritos del usuario.
     *
     * @param userId ID del usuario en Firestore.
     * @param gameId ID del videojuego que se agregará a favoritos.
     * @return Una tarea que indica el estado de la actualización.
     */
    public Task<Void> addFavorite(String userId, String gameId) {
        DocumentReference userRef = db.collection("usuarios").document(userId);
        return userRef.update("favoritos", FieldValue.arrayUnion(gameId)); // Agrega el ID del juego al array de favoritos
    }

    /**
     * Elimina un videojuego de la lista de favoritos del usuario.
     *
     * @param userId ID del usuario en Firestore.
     * @param gameId ID del videojuego que se eliminará de favoritos.
     * @return Una tarea que indica el estado de la actualización.
     */
    public Task<Void> removeFavorite(String userId, String gameId) {
        DocumentReference userRef = db.collection("usuarios").document(userId);
        return userRef.update("favoritos", FieldValue.arrayRemove(gameId)); // Elimina el ID del juego del array de favoritos
    }

    /**
     * Obtiene la lista de videojuegos favoritos del usuario.
     *
     * @param userId ID del usuario en Firestore.
     * @return Una tarea con el documento del usuario que contiene la lista de favoritos.
     */
    public Task<DocumentSnapshot> getUserFavorites(String userId) {
        return db.collection("usuarios").document(userId).get(); // Obtiene el documento del usuario
    }
}
