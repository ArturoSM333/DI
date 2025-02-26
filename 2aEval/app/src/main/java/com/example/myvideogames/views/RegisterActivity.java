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

        // Inicializar el ViewModel para la gestión del registro
        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        // Referencias a los EditText para la entrada de usuario
        EditText emailEditText = findViewById(R.id.emailEditText);
        EditText passwordEditText = findViewById(R.id.passwordEditText);
        EditText confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);

        // Acción al hacer clic en el botón de registrar
        findViewById(R.id.registerButton).setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();
            String confirmPassword = confirmPasswordEditText.getText().toString().trim();

            // Registrar al usuario con los datos proporcionados
            registerViewModel.registerUser(email, password, confirmPassword);
        });

        // Acción para redirigir a la pantalla de login si ya tiene cuenta
        findViewById(R.id.loginRedirectButton).setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        // Observar el estado del registro
        observeViewModel();
    }

    private void observeViewModel() {
        // Observador para manejar los estados del registro
        registerViewModel.getRegisterStatus().observe(this, status -> {
            if (status != null) {
                Toast.makeText(RegisterActivity.this, status, Toast.LENGTH_SHORT).show();

                // Si el registro es exitoso, redirigir al login
                if (status.equals("Usuario registrado correctamente.")) {
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        // Aquí podrías mostrar u ocultar un indicador de carga si se desea
        registerViewModel.getIsLoading().observe(this, isLoading -> {
            // Lógica para mostrar o esconder un indicador de carga
        });
    }
}
