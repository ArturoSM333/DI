package com.example.myvideogames.views;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import androidx.appcompat.widget.Toolbar;  // Importa el Toolbar
import com.example.myvideogames.R;
import com.example.myvideogames.views.DashboardFragment;
import com.example.myvideogames.views.FavouritesFragment;
import com.example.myvideogames.views.ProfileFragment;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);

        // Configurar el Toolbar y el botón de abrir/cerrar el drawer
        Toolbar toolbar = findViewById(R.id.toolbar);  // Asegúrate de tener un toolbar en tu layout
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Muestra el ícono del menú
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_launcher_foreground); // Ajusta el ícono del menú

        // Cargar DashboardFragment por defecto
        if (savedInstanceState == null) {
            loadFragment(new DashboardFragment());
        }

        // Configurar los ítems del menú
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.dashboard) {
                loadFragment(new DashboardFragment());
            } else if (id == R.id.favourites) {
                loadFragment(new FavouritesFragment());
            } else if (id == R.id.profile) {
                loadFragment(new ProfileFragment());
            } else if (id == R.id.logout) {
                logout();
            }

            // Cerrar el Navigation Drawer después de seleccionar un ítem
            drawerLayout.closeDrawers();
            return true;
        });
    }

    // Método para cargar los fragments
    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null); // Opcional: para permitir que el usuario regrese
        transaction.commit();
    }

    // Método para hacer logout
    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    // Método para abrir el drawer cuando se haga clic en el icono de menú
    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
