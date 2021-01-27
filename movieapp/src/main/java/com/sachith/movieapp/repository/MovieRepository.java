package com.sachith.movieapp.repository;

import com.sachith.movieapp.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    Movie save(Movie movie);
    Movie findByName(String name);
    Movie deleteById(int id);
    ArrayList<Movie> getAllByStatusTrue();
}
