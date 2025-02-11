package com.example.myvideogames.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.myvideogames.R;
import com.example.myvideogames.models.Game;

public class DetailFragment extends Fragment {

    private TextView titleTextView;
    private TextView descriptionTextView;
    private ImageView gameImageView;
    private Game selectedGame;

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            selectedGame = (Game) getArguments().getSerializable("game");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        titleTextView = rootView.findViewById(R.id.detailTitleTextView);
        descriptionTextView = rootView.findViewById(R.id.detailDescriptionTextView);
        gameImageView = rootView.findViewById(R.id.detailImageView);

        // Set data to views
        if (selectedGame != null) {
            titleTextView.setText(selectedGame.getTitulo());
            descriptionTextView.setText(selectedGame.getDescripcion());
            // Load image (using a library like Glide or Picasso for real app)
            gameImageView.setImageResource(R.drawable.ic_launcher_background); // Placeholder
        }

        return rootView;
    }
}
