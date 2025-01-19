package com.example.myvideogames;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        // Verifica si el usuario ya está autenticado
        if (mAuth.getCurrentUser() == null) {
            // Si no hay un usuario autenticado, redirige a LoginActivity
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish(); // Finaliza MainActivity para que no quede en el stack
        } else {
            // Si hay un usuario autenticado, muestra un mensaje de bienvenida
            String userEmail = mAuth.getCurrentUser().getEmail();
            Toast.makeText(this, "Bienvenido: " + userEmail, Toast.LENGTH_SHORT).show();
        }

        // Botón para cerrar sesión
        findViewById(R.id.logoutButton).setOnClickListener(v -> logoutUser());
    }

    private void logoutUser() {
        mAuth.signOut(); // Cierra la sesión del usuario actual
        Toast.makeText(this, "Sesión cerrada.", Toast.LENGTH_SHORT).show();

        // Redirige al usuario a la pantalla de inicio de sesión
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish(); // Finaliza MainActivity para evitar que el usuario regrese
    }
}