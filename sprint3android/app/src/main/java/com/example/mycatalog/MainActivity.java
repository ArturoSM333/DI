package com.example.mycatalog;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configurar el BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Establecer el fragmento por defecto
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CatalogFragment()).commit();

        // Listener para cambiar entre fragmentos
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_catalog:
                    selectedFragment = new CatalogFragment();
                    break;
                case R.id.navigation_about:
                    selectedFragment = new AboutFragment();
                    break;
            }
            // Reemplazar el fragmento en el contenedor
            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            }
            return true;
        });
    }
}
