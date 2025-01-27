package com.example.myvideogames.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.myvideogames.R;
import com.example.myvideogames.viewmodels.RegisterViewModel;

public class RegisterActivity extends AppCompatActivity {
    private RegisterViewModel registerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        EditText emailEditText = findViewById(R.id.emailEditText);
        EditText passwordEditText = findViewById(R.id.passwordEditText);
        EditText confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);

        findViewById(R.id.registerButton).setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();
            String confirmPassword = confirmPasswordEditText.getText().toString().trim();

            registerViewModel.registerUser(email, password, confirmPassword);
        });

        findViewById(R.id.loginRedirectButton).setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        observeViewModel();
    }

    private void observeViewModel() {
        registerViewModel.getRegisterStatus().observe(this, status -> {
            if (status != null) {
                Toast.makeText(RegisterActivity.this, status, Toast.LENGTH_SHORT).show();

                if (status.equals("Usuario registrado correctamente.")) {
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        registerViewModel.getIsLoading().observe(this, isLoading -> {
            // Aquí podrías mostrar u ocultar un indicador de carga si lo tienes en la UI
        });
    }
}
