package com.example.mycatalog;

import android.content.Intent; // Necesario para la navegación entre actividades
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CatalogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog); // Vinculamos el diseño XML
        // Encontramos el botón por su ID
        Button btnNavigateToDetail = findViewById(R.id.btnNavigateToDetail);
        // Establecemos un OnClickListener para el botón
        btnNavigateToDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Muestra un mensaje Toast al hacer clic en el botón
                Toast.makeText(CatalogActivity.this, "Navegando al detalle...", Toast.LENGTH_SHORT).show();
                // Aquí puedes agregar la lógica para navegar a una nueva actividad (detalle)
                // Por ejemplo, si tienes una actividad llamada DetailActivity:
                Intent intent = new Intent(CatalogActivity.this, DetailActivity.class);
                startActivity(intent);
            }
        });
    }
}