package com.example.myvideogames.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterViewModel extends ViewModel {
    // Instancia de FirebaseAuth para la autenticación de usuarios
    private final FirebaseAuth auth;

    // LiveData para almacenar el estado del registro (mensajes)
    private final MutableLiveData<String> registerStatus = new MutableLiveData<>();

    // LiveData para manejar el estado de carga (si está esperando la respuesta de Firebase)
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    // Constructor que inicializa FirebaseAuth
    public RegisterViewModel() {
        auth = FirebaseAuth.getInstance();
    }

    // Método para obtener el estado del registro
    public LiveData<String> getRegisterStatus() {
        return registerStatus;
    }

    // Método para obtener el estado de carga (true si está cargando, false si no)
    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    // Método para registrar un nuevo usuario
    public void registerUser(String email, String password, String confirmPassword) {
        // Verificar si todos los campos están completos
        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            registerStatus.setValue("Por favor, completa todos los campos.");
            return;
        }

        // Verificar que las contraseñas coincidan
        if (!password.equals(confirmPassword)) {
            registerStatus.setValue("Las contraseñas no coinciden.");
            return;
        }

        // Verificar que la contraseña tenga al menos 6 caracteres
        if (password.length() < 6) {
            registerStatus.setValue("La contraseña debe tener al menos 6 caracteres.");
            return;
        }

        // Indicar que el proceso de registro está en carga
        isLoading.setValue(true);

        // Intentar crear un nuevo usuario con correo y contraseña
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    // Finalizar el estado de carga
                    isLoading.setValue(false);

                    // Verificar si la creación del usuario fue exitosa
                    if (task.isSuccessful()) {
                        registerStatus.setValue("Usuario registrado correctamente.");
                    } else {
                        // Si hubo un error, mostrar el mensaje de error
                        registerStatus.setValue("Error en el registro: " + task.getException().getMessage());
                    }
                });
    }
}
