package com.example.topmoviesapp;

import android.provider.ContactsContract;

import java.util.List;

public class Movie {
    private String _id;
    private String id;
    private String __v;
    private String description;
    private List<String> director;
    private List<String> genre;
    private String[][] image;
    private String imdbid;
    private int rank;
    private String rating;
    private String thumbnail;
    private String title;
    private List<String> writers;
    private int year;

    public Movie(String title, int year) {
        this.title = title;
        this.year = year;
    }

    public String getTitle() {
        return this.title;
    }

    public int getYear() {
        return this.year;
    }
}
