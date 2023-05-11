package com.example.topmoviesapp;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    private Context context;
    private List<Movie> movieList = new ArrayList<>();
    private RecyclerViewInterface recyclerViewInterface;

    public MovieAdapter(Context context, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
//        this.movieList = movieList;
        this.recyclerViewInterface = recyclerViewInterface;
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
        holder.movieName.setText(movie.getTitle());
        holder.movieYear.setText(String.valueOf(movie.getYear()));
        Glide.with(context).load(movie.getImage()).into(holder.movieImage);
    }

    @Override
    public int getItemCount() {
        return this.movieList.size();
    }

    public void setMovies(List<Movie>movies){
        this.movieList.clear();
        this.movieList.addAll(movies);
        notifyDataSetChanged();
    }

    public class MovieHolder extends RecyclerView.ViewHolder{
        TextView movieName, movieYear;
        ImageView movieImage;
        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            movieName = itemView.findViewById(R.id.movieNameItemPage);
            movieYear = itemView.findViewById(R.id.movieYearItemPage);
            movieImage = itemView.findViewById(R.id.movieImageItemPage);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recyclerViewInterface != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
