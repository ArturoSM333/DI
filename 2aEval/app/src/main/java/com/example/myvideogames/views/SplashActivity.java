package com.example.myvideogames.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myvideogames.R;
import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Esperar 2 segundos antes de redirigir
        new Handler().postDelayed(() -> {
            // Verificar SharedPreferences para saber si ya hay un usuario autenticado
            SharedPreferences sharedPref = getSharedPreferences("AppConfig", MODE_PRIVATE);
            String userId = sharedPref.getString("userId", null);

            if (userId != null) {
                // Usuario almacenado, redirigir a MainActivity
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
            } else {
                // Si no hay usuario guardado, se comprueba FirebaseAuth como respaldo
                if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
            finish();
        }, 2000);
    }
}
