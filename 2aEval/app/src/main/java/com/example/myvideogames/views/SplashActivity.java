package com.example.myvideogames.views;

import android.content.Intent;
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

        // Después de 2 segundos, redirigir al usuario a MainActivity o LoginActivity
        new Handler().postDelayed(() -> {
            // Comprobar si el usuario está autenticado
            if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                // Usuario autenticado, redirigir a MainActivity
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
            } else {
                // Usuario no autenticado, redirigir a LoginActivity
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
            }
            finish(); // Termina SplashActivity para que no regrese
        }, 2000); // 2 segundos de duración de la Splash Screen
    }
}
