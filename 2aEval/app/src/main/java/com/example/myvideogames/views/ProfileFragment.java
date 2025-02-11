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

    private Switch themeSwitch;
    private Button changePasswordButton;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        themeSwitch = rootView.findViewById(R.id.themeSwitch);
        changePasswordButton = rootView.findViewById(R.id.changePasswordButton);

        // Set the switch state based on SharedPreferences (saved theme)
        SharedPreferences prefs = getActivity().getSharedPreferences("userPrefs", Context.MODE_PRIVATE);
        boolean isDarkMode = prefs.getBoolean("isDarkMode", false);
        themeSwitch.setChecked(isDarkMode);

        themeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Save the theme preference
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("isDarkMode", isChecked);
            editor.apply();

            // Change theme based on the switch state
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
        });

        // Cambiar la contraseña
        changePasswordButton.setOnClickListener(v -> showChangePasswordDialog());

        return rootView;
    }

    // Método para mostrar el cuadro de diálogo para cambiar la contraseña
    private void showChangePasswordDialog() {
        // Crear un cuadro de diálogo
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Cambiar contraseña");

        // Crear un EditText para ingresar la nueva contraseña
        final EditText input = new EditText(getContext());
        input.setHint("Nueva contraseña");
        builder.setView(input);

        // Botón para confirmar el cambio de contraseña
        builder.setPositiveButton("Cambiar", (dialog, which) -> {
            String newPassword = input.getText().toString().trim();

            if (!newPassword.isEmpty()) {
                changePassword(newPassword);
            } else {
                Toast.makeText(getContext(), "La contraseña no puede estar vacía", Toast.LENGTH_SHORT).show();
            }
        });

        // Botón para cancelar el cambio de contraseña
        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    // Método para cambiar la contraseña
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
