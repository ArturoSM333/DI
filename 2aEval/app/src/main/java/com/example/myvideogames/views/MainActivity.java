package com.example.myvideogames.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import androidx.appcompat.widget.Toolbar;
import com.example.myvideogames.R;
import com.example.myvideogames.views.DashboardFragment;
import com.example.myvideogames.views.FavouritesFragment;
import com.example.myvideogames.views.ProfileFragment;
import com.example.myvideogames.utils.FavoritesManager;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private FavoritesManager favoritesManager; // Instancia para gestionar favoritos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar componentes del Navigation Drawer
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);

        // Inicializar el FavoritesManager
        favoritesManager = new FavoritesManager(getApplication());

        // Configurar el Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_launcher_foreground);

        // Cargar el DashboardFragment por defecto
        if (savedInstanceState == null) {
            loadFragment(new DashboardFragment());
        }

        // Configurar el listener del NavigationDrawer
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.dashboard) {
                loadFragment(new DashboardFragment());
            } else if (id == R.id.favourites) {
                loadFragment(new FavouritesFragment());
            } else if (id == R.id.profile) {
                loadFragment(new ProfileFragment());
            } else if (id == R.id.clear_favorites) {
                // Llamar al método para limpiar todos los favoritos
                favoritesManager.clearFavorites();
                Toast.makeText(MainActivity.this, "Favoritos limpiados", Toast.LENGTH_SHORT).show();
                // Recargar el DashboardFragment para mostrar todos los juegos
                loadFragment(new DashboardFragment());
            } else if (id == R.id.logout) {
                logout();
            }

            drawerLayout.closeDrawers();
            return true;
        });
    }

    // Método para cargar un fragmento en el contenedor
    private void loadFragment(androidx.fragment.app.Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    // Método para cerrar sesión y eliminar el usuario de SharedPreferences
    private void logout() {
        FirebaseAuth.getInstance().signOut();
        // Eliminar el UID almacenado en SharedPreferences
        SharedPreferences sharedPref = getSharedPreferences("AppConfig", MODE_PRIVATE);
        sharedPref.edit().remove("userId").apply();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
