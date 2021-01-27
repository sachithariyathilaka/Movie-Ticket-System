package com.sachith.movieapp.dto;

import com.sachith.movieapp.model.Movie;

import java.util.ArrayList;

public class MovieResponseDto {
    private int status;
    private String message;
    private ArrayList<Movie> movies;

    public MovieResponseDto(int status, String message, ArrayList<Movie> movies) {
        this.status = status;
        this.message = message;
        this.movies = movies;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }
}
