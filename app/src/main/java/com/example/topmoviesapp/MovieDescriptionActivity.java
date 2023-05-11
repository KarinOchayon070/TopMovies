package com.example.topmoviesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

public class MovieDescriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_description);

        String movieName = getIntent().getStringExtra("movieName");
        String movieGenre = getIntent().getStringExtra("movieGenre");
        String movieDescription = getIntent().getStringExtra("movieDescription");
        String movieRating = getIntent().getStringExtra("movieRating");
        String movieYear = getIntent().getStringExtra("movieYear");
        String movieImage = getIntent().getStringExtra("movieImage");
        String movieDirector = getIntent().getStringExtra("movieDirector");

        TextView movieNameDescriptionPage = findViewById(R.id.movieNameDescriptionPage);
        movieNameDescriptionPage.setText(movieName);

        TextView movieGenreDescriptionPage = findViewById(R.id.movieGenreDescriptionPage);
        movieGenreDescriptionPage.setText(movieGenre);

        TextView movieSumDescriptionPage = findViewById(R.id.movieSumDescriptionPage);
        movieSumDescriptionPage.setText(movieDescription);

        TextView movieRatingDescriptionPage = findViewById(R.id.movieRatingDescriptionPage);
        movieRatingDescriptionPage.setText(movieRating);

        TextView movieYearDescriptionPage = findViewById(R.id.movieYearDescriptionPage);
        movieYearDescriptionPage.setText(movieYear);

        ImageView movieImageDescriptionPage = findViewById(R.id.movieImageDescriptionPage);
        Glide.with(this).load(movieImage).into(movieImageDescriptionPage);

        TextView movieDirectorDescriptionPage = findViewById(R.id.movieDirectorDescriptionPage);
        movieDirectorDescriptionPage.setText(movieDirector);
    }
}