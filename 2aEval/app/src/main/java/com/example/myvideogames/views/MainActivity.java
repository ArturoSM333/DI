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

    private DrawerLayout drawerLayout; // Layout principal con el Navigation Drawer
    private NavigationView navigationView; // Vista de navegación para los ítems del menú

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar los componentes del Navigation Drawer
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);

        // Configurar el Toolbar y habilitar el ícono de menú para abrir el drawer
        Toolbar toolbar = findViewById(R.id.toolbar);  // Asegúrate de tener un toolbar en tu layout
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Muestra el ícono del menú
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_launcher_foreground); // Ajusta el ícono del menú

        // Cargar el DashboardFragment como la pantalla principal al inicio
        if (savedInstanceState == null) {
            loadFragment(new DashboardFragment()); // Carga el fragmento por defecto
        }

        // Configurar la acción al hacer clic en los ítems del menú del drawer
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            // Cargar diferentes fragmentos dependiendo del ítem seleccionado
            if (id == R.id.dashboard) {
                loadFragment(new DashboardFragment());
            } else if (id == R.id.favourites) {
                loadFragment(new FavouritesFragment());
            } else if (id == R.id.profile) {
                loadFragment(new ProfileFragment());
            } else if (id == R.id.logout) {
                logout();  // Cerrar sesión si el usuario elige logout
            }

            // Cerrar el Navigation Drawer después de seleccionar un ítem
            drawerLayout.closeDrawers();
            return true;
        });
    }

    // Método para cargar un fragmento en el contenedor
    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment); // Reemplaza el fragment actual
        transaction.addToBackStack(null); // Añade el fragmento a la pila de retroceso (opcional)
        transaction.commit(); // Realiza la transacción
    }

    // Método para cerrar sesión y redirigir al login
    private void logout() {
        FirebaseAuth.getInstance().signOut();  // Cerrar sesión en Firebase
        Intent intent = new Intent(MainActivity.this, LoginActivity.class); // Redirige a LoginActivity
        startActivity(intent);
        finish();  // Finaliza la actividad actual para evitar que se regrese a ella
    }

    // Método para abrir el drawer cuando se haga clic en el ícono de menú
    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);  // Abre el drawer al hacer clic en el ícono del menú
            return true;
        }
        return super.onOptionsItemSelected(item);  // Llama al super para otras acciones
    }
}
