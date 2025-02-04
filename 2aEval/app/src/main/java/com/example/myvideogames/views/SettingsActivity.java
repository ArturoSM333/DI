package com.example.myvideogames.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.myvideogames.R;

public class SettingsActivity extends AppCompatActivity {

    private Button themeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        themeButton = findViewById(R.id.themeButton);
        Button backButton = findViewById(R.id.backButton);

        // Recuperar la preferencia del tema desde SharedPreferences
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isDarkModeEnabled = sharedPreferences.getBoolean("dark_mode", false);

        // Establecer el estado del botón y el tema según la preferencia almacenada
        setButtonTextAndTheme(isDarkModeEnabled);

        // Cambiar el tema al hacer clic en el botón
        themeButton.setOnClickListener(v -> {
            boolean isCurrentlyDarkMode = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES;
            boolean newMode = !isCurrentlyDarkMode;

            // Actualizar el tema
            setAppTheme(newMode);

            // Actualizar el texto del botón y guardar la preferencia
            setButtonTextAndTheme(newMode);

            // Guardar la preferencia en SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("dark_mode", newMode);
            editor.apply();
        });


        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(SettingsActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }

    // Establecer el texto del botón y el tema
    private void setButtonTextAndTheme(boolean isDarkMode) {
        if (isDarkMode) {
            themeButton.setText("Activar Modo Claro");
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            themeButton.setText("Activar Modo Oscuro");
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    // Establecer el modo de tema
    private void setAppTheme(boolean isDarkMode) {
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}
