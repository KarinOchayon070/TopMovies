package com.example.topmoviesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MovieDescriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_description);

        String movieName = getIntent().getStringExtra("movieName");
        String movieGenre = getIntent().getStringExtra("movieGenre");

        TextView movieNameDescriptionPage = findViewById(R.id.movieNameDescriptionPage);
        movieNameDescriptionPage.setText(movieName);

        TextView movieGenreDescriptionPage = findViewById(R.id.movieGenreDescriptionPage);
        movieGenreDescriptionPage.setText(movieGenre);
    }
}