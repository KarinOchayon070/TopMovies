package com.example.topmoviesapp;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    private Context context;
    private List<Movie> movieList = new ArrayList<>();


    public MovieAdapter(Context context) {
        this.context = context;
//        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        Movie movie = this.movieList.get(position);
        holder.movieName.setText("BLA");
        Log.d("movienamebigtag", String.valueOf(holder.movieName.getText()));
        holder.movieYear.setText(String.valueOf(movie.getYear()));
        Log.d("movieyearbigtag", String.valueOf(holder.movieYear.getText()));


    }

    @Override
    public int getItemCount() {
        return this.movieList.size();
    }

    public void setMovies(List<Movie>movies){
        this.movieList.addAll(movies);
    }

    public class MovieHolder extends RecyclerView.ViewHolder{
        TextView movieName, movieYear;
        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            movieName = itemView.findViewById(R.id.movieNameItemPage);
            movieYear = itemView.findViewById(R.id.movieYearItemPage);
        }
    }
}
