package com.example.topmoviesapp;

import java.util.List;

public class MovieApiResponse {

    private List<Movie> results;

    public MovieApiResponse(List<Movie> results) {
        this.results = results;
    }

    public List<Movie> getResults() {
        return results;
    }
}
