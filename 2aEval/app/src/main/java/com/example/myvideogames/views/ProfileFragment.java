package com.example.myvideogames.views;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;

import com.example.myvideogames.R;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileFragment extends Fragment {

    private Switch themeSwitch;  // Switch para cambiar entre tema claro y oscuro
    private Button changePasswordButton;  // Botón para cambiar la contraseña

    public ProfileFragment() {
        // Constructor vacío requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        // Inicializar los componentes
        themeSwitch = rootView.findViewById(R.id.themeSwitch);
        changePasswordButton = rootView.findViewById(R.id.changePasswordButton);

        // Obtener la preferencia de tema guardada en SharedPreferences
        SharedPreferences prefs = getActivity().getSharedPreferences("userPrefs", Context.MODE_PRIVATE);
        boolean isDarkMode = prefs.getBoolean("isDarkMode", false);
        themeSwitch.setChecked(isDarkMode);  // Establecer el estado del switch

        // Listener para cambiar el estado del tema
        themeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Guardar la preferencia del tema
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("isDarkMode", isChecked);
            editor.apply();

            // Cambiar el tema basado en el estado del switch
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);  // Modo oscuro
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);  // Modo claro
            }
            requireActivity().recreate();  // Recrear la actividad para aplicar el cambio de tema
        });

        // Configurar el botón para cambiar la contraseña
        changePasswordButton.setOnClickListener(v -> showChangePasswordDialog());

        return rootView;
    }

    // Método para mostrar el cuadro de diálogo para cambiar la contraseña
    private void showChangePasswordDialog() {
        // Crear un cuadro de diálogo con un campo de texto para ingresar la nueva contraseña
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Cambiar contraseña");

        // Crear un EditText para la nueva contraseña
        final EditText input = new EditText(getContext());
        input.setHint("Nueva contraseña");
        builder.setView(input);

        // Botón para confirmar el cambio de contraseña
        builder.setPositiveButton("Cambiar", (dialog, which) -> {
            String newPassword = input.getText().toString().trim();

            if (!newPassword.isEmpty()) {
                changePassword(newPassword);  // Llamar al método para cambiar la contraseña
            } else {
                Toast.makeText(getContext(), "La contraseña no puede estar vacía", Toast.LENGTH_SHORT).show();
            }
        });

        // Botón para cancelar la acción
        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel());

        builder.show();  // Mostrar el cuadro de diálogo
    }

    // Método para cambiar la contraseña en Firebase
    private void changePassword(String newPassword) {
        FirebaseAuth.getInstance().getCurrentUser().updatePassword(newPassword)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(getContext(), "Contraseña cambiada con éxito", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Error al cambiar la contraseña", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
