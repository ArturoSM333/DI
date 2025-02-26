package com.example.myvideogames.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;

public class LoginViewModel extends ViewModel {
    // Instancia de FirebaseAuth para la autenticación de usuarios
    private final FirebaseAuth auth;

    // LiveData para almacenar el estado del inicio de sesión (mensajes)
    private final MutableLiveData<String> loginStatus = new MutableLiveData<>();

    // LiveData para manejar el estado de carga (si está esperando la respuesta de Firebase)
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    // Constructor que inicializa FirebaseAuth
    public LoginViewModel() {
        auth = FirebaseAuth.getInstance();
    }

    // Método para obtener el estado del inicio de sesión
    public LiveData<String> getLoginStatus() {
        return loginStatus;
    }

    // Método para obtener el estado de carga (true si está cargando, false si no)
    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    // Método para iniciar sesión con correo y contraseña
    public void loginUser(String email, String password) {
        // Verificar si los campos de correo y contraseña no están vacíos
        if (email.isEmpty() || password.isEmpty()) {
            loginStatus.setValue("Por favor, completa todos los campos.");
            return;
        }

        // Indicar que el proceso de inicio de sesión está en carga
        isLoading.setValue(true);

        // Realizar el intento de inicio de sesión con Firebase
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    // Finalizar el estado de carga
                    isLoading.setValue(false);

                    // Verificar si la autenticación fue exitosa
                    if (task.isSuccessful()) {
                        loginStatus.setValue("Inicio de sesión exitoso.");
                    } else {
                        // Si hubo un error, mostrar el mensaje de error
                        loginStatus.setValue("Error en autenticación: " + task.getException().getMessage());
                    }
                });
    }
}
