package com.example.myvideogames.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;

import com.example.myvideogames.R;
import com.example.myvideogames.viewmodels.LoginViewModel;

public class LoginActivity extends AppCompatActivity {
    private LoginViewModel loginViewModel;

    private Button settingsButton; // Botón para abrir la configuración

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Recuperar la preferencia de modo oscuro desde SharedPreferences
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isDarkModeEnabled = sharedPreferences.getBoolean("dark_mode", false);

        // Establecer el tema según la preferencia guardada
        if (isDarkModeEnabled) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES); // Activar modo oscuro
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO); // Activar modo claro
        }

        // Cargar el layout de LoginActivity
        setContentView(R.layout.activity_login);

        // Configurar el botón de configuración para abrir la actividad de configuración
        settingsButton = findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SettingsActivity.class); // Redirigir a SettingsActivity
            startActivity(intent);
        });

        // Inicializar el ViewModel para manejar la lógica del inicio de sesión
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        // Referencias a los campos de texto para ingresar el email y la contraseña
        EditText emailEditText = findViewById(R.id.emailEditText);
        EditText passwordEditText = findViewById(R.id.passwordEditText);

        // Configurar el botón de inicio de sesión
        findViewById(R.id.loginButton).setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim(); // Obtener el email
            String password = passwordEditText.getText().toString().trim(); // Obtener la contraseña

            // Llamar al método de login en el ViewModel
            loginViewModel.loginUser(email, password);
        });

        // Configurar el botón para redirigir al registro de usuarios
        findViewById(R.id.registerRedirectButton).setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class); // Redirigir a RegisterActivity
            startActivity(intent);
        });

        // Observar los cambios en el estado del login desde el ViewModel
        observeViewModel();
    }

    // Método para observar los LiveData del ViewModel
    private void observeViewModel() {
        // Observamos el estado de inicio de sesión
        loginViewModel.getLoginStatus().observe(this, status -> {
            if (status != null) {
                // Mostrar el mensaje de estado del login
                Toast.makeText(LoginActivity.this, status, Toast.LENGTH_SHORT).show();

                // Si el inicio de sesión es exitoso, redirigir a la pantalla principal
                if (status.equals("Inicio de sesión exitoso.")) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class); // Redirigir a MainActivity
                    startActivity(intent);
                    finish(); // Finalizar LoginActivity para que no vuelva al presionar atrás
                }
            }
        });

        // Observar el estado de carga para mostrar u ocultar un indicador de carga si es necesario
        loginViewModel.getIsLoading().observe(this, isLoading -> {
            // Aquí puedes mostrar u ocultar un indicador de carga si el estado es 'isLoading'
        });
    }
}
