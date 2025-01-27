package com.example.myvideogames.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;

public class LoginViewModel extends ViewModel {
    private final FirebaseAuth auth;
    private final MutableLiveData<String> loginStatus = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public LoginViewModel() {
        auth = FirebaseAuth.getInstance();
    }

    public LiveData<String> getLoginStatus() {
        return loginStatus;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public void loginUser(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            loginStatus.setValue("Por favor, completa todos los campos.");
            return;
        }

        isLoading.setValue(true);

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    isLoading.setValue(false);
                    if (task.isSuccessful()) {
                        loginStatus.setValue("Inicio de sesión exitoso.");
                    } else {
                        loginStatus.setValue("Error en autenticación: " + task.getException().getMessage());
                    }
                });
    }
}
