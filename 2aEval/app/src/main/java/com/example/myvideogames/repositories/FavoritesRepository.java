package com.example.myvideogames.repositories;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FieldValue;
import com.google.android.gms.tasks.Task;

public class FavoritesRepository {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public Task<Void> addFavorite(String userId, String gameId) {
        DocumentReference userRef = db.collection("usuarios").document(userId);
        return userRef.update("favoritos", FieldValue.arrayUnion(gameId));
    }

    public Task<Void> removeFavorite(String userId, String gameId) {
        DocumentReference userRef = db.collection("usuarios").document(userId);
        return userRef.update("favoritos", FieldValue.arrayRemove(gameId));
    }

    public Task<DocumentSnapshot> getUserFavorites(String userId) {
        return db.collection("usuarios").document(userId).get();
    }
}
