package com.example.myvideogames.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterViewModel extends ViewModel {
    private final FirebaseAuth auth;
    private final MutableLiveData<String> registerStatus = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public RegisterViewModel() {
        auth = FirebaseAuth.getInstance();
    }

    public LiveData<String> getRegisterStatus() {
        return registerStatus;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public void registerUser(String email, String password, String confirmPassword) {
        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            registerStatus.setValue("Por favor, completa todos los campos.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            registerStatus.setValue("Las contraseñas no coinciden.");
            return;
        }

        if (password.length() < 6) {
            registerStatus.setValue("La contraseña debe tener al menos 6 caracteres.");
            return;
        }

        isLoading.setValue(true);

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    isLoading.setValue(false);
                    if (task.isSuccessful()) {
                        registerStatus.setValue("Usuario registrado correctamente.");
                    } else {
                        registerStatus.setValue("Error en el registro: " + task.getException().getMessage());
                    }
                });
    }
}
