package com.example.myvideogames.views;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myvideogames.R;

public class SettingsActivity extends AppCompatActivity {
    private SharedPreferences prefs;
    private Switch darkModeSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        prefs = getSharedPreferences("settings", MODE_PRIVATE);
        boolean darkMode = prefs.getBoolean("dark_mode", false);

        darkModeSwitch = findViewById(R.id.darkModeSwitch);
        darkModeSwitch.setChecked(darkMode);

        darkModeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("dark_mode", isChecked);
            editor.apply();
        });
    }
}