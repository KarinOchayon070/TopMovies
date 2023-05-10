package com.example.topmoviesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter = new MovieAdapter(MainActivity.this);
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
                if(movieList.isEmpty()){
                    Log.d("emptytagcheck", "Movie list is empty");
                }
                else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            movieAdapter.setMovies(movieList);
                            recyclerView.setAdapter(movieAdapter);
                        }
                    });
                }
            }

        });
    }
    private List<Movie> parseJsonToMovie(String responseBody){
        Gson gson = new Gson();
        Type movieListType = new TypeToken<List<Movie>>(){}.getType();
        List<Movie> movies = gson.fromJson(responseBody, movieListType);
        Log.d("testing",movies.get(2).getTitle());
        return movies;
    }
}