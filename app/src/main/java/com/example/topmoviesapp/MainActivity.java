package com.example.topmoviesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface {

    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter = new MovieAdapter(MainActivity.this, this);
    private List<Movie>movieList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fetchMoviesFromAPI();
    }

    private void fetchMoviesFromAPI(){

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://imdb-top-100-movies1.p.rapidapi.com/")
                .get()
                .addHeader("X-RapidAPI-Key", "1e5c2595e5mshc18e0b33083353ap195da8jsn6d7b91d03321")
                .addHeader("X-RapidAPI-Host", "imdb-top-100-movies1.p.rapidapi.com")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String responseBody = response.body().string();
                Log.d("tag", responseBody);
                movieList = parseJsonToMovie(responseBody);
                if(!movieList.isEmpty()){
                    Collections.sort(movieList, new Comparator<Movie>() {
                        @Override
                        public int compare(Movie m1, Movie m2) {
                            return m2.getYear() - m1.getYear();
                        }
                    });
                    movieAdapter.setMovies(movieList);
                    runOnUiThread(()->recyclerView.setAdapter(movieAdapter));
                }
            }

        });
    }
    private List<Movie> parseJsonToMovie(String responseBody){
        Gson gson = new Gson();
        Type movieListType = new TypeToken<List<Movie>>(){}.getType();
        List<Movie> movies = gson.fromJson(responseBody, movieListType);
        return movies;
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(MainActivity.this, MovieDescriptionActivity.class);
        intent.putExtra("movieName", movieList.get(position).getTitle());
        List<String> genreList = movieList.get(position).getGenre();
        String genreString = TextUtils.join(", ", genreList);
        intent.putExtra("movieGenre", genreString);
        intent.putExtra("movieRating", movieList.get(position).getRating());
        intent.putExtra("movieDescription", movieList.get(position).getDescription());
        intent.putExtra("movieYear", String.valueOf(movieList.get(position).getYear()));
        intent.putExtra("movieImage", movieList.get(position).getImage());
        List<String> directorList = movieList.get(position).getDirector();
        String directorString = TextUtils.join(",", directorList);
        intent.putExtra("movieDirector", directorString);
        startActivity(intent);
    }
}