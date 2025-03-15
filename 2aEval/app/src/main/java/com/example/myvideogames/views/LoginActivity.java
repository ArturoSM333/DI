package com.example.myvideogames.views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.myvideogames.R;
import com.example.myvideogames.viewmodels.LoginViewModel;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Configuración de botones, vistas y settings (omitiendo parte del código original para centrarnos en el cambio)
        // ...

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        EditText emailEditText = findViewById(R.id.emailEditText);
        EditText passwordEditText = findViewById(R.id.passwordEditText);

        findViewById(R.id.loginButton).setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();
            loginViewModel.loginUser(email, password);
        });

        findViewById(R.id.registerRedirectButton).setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        observeViewModel();
    }

    private void observeViewModel() {
        loginViewModel.getLoginStatus().observe(this, status -> {
            if (status != null) {
                Toast.makeText(LoginActivity.this, status, Toast.LENGTH_SHORT).show();
                if (status.equals("Inicio de sesión exitoso.")) {
                    // Guardar el UID del usuario autenticado en SharedPreferences
                    SharedPreferences sharedPref = getSharedPreferences("AppConfig", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("userId", FirebaseAuth.getInstance().getCurrentUser().getUid());
                    editor.apply();

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        loginViewModel.getIsLoading().observe(this, isLoading -> {
            // Mostrar o esconder indicador de carga si es necesario
        });
    }
}
