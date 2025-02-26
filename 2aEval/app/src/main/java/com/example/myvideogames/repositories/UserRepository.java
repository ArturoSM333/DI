package com.example.myvideogames.repositories;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Repositorio para gestionar la autenticación de usuarios en Firebase.
 */
public class UserRepository {

    private FirebaseAuth mAuth; // Instancia de Firebase Authentication

    /**
     * Constructor que inicializa Firebase Authentication.
     */
    public UserRepository() {
        mAuth = FirebaseAuth.getInstance();
    }

    /**
     * Registra un nuevo usuario con correo y contraseña.
     *
     * @param email Correo electrónico del usuario.
     * @param password Contraseña del usuario.
     * @param listener Interfaz de callback para manejar el éxito o el fallo del registro.
     */
    public void registerUser(String email, String password, OnAuthCompleteListener listener) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Si el registro es exitoso, se obtiene el usuario actual
                        FirebaseUser user = mAuth.getCurrentUser();
                        listener.onSuccess(user);
                    } else {
                        // Si falla, se pasa la excepción al callback
                        listener.onFailure(task.getException());
                    }
                });
    }

    /**
     * Inicia sesión con correo y contraseña.
     *
     * @param email Correo electrónico del usuario.
     * @param password Contraseña del usuario.
     * @param listener Interfaz de callback para manejar el éxito o el fallo del inicio de sesión.
     */
    public void loginUser(String email, String password, OnAuthCompleteListener listener) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Si el inicio de sesión es exitoso, se obtiene el usuario actual
                        FirebaseUser user = mAuth.getCurrentUser();
                        listener.onSuccess(user);
                    } else {
                        // Si falla, se pasa la excepción al callback
                        listener.onFailure(task.getException());
                    }
                });
    }

    /**
     * Interfaz para manejar los resultados de la autenticación (éxito o fallo).
     */
    public interface OnAuthCompleteListener {
        void onSuccess(FirebaseUser user); // Se ejecuta si el usuario se autentica correctamente
        void onFailure(Exception e); // Se ejecuta si ocurre un error
    }
}
