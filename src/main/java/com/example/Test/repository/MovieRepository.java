package com.example.Test.repository;

import com.example.Test.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie,Integer> {
    Optional<Movie> findByRatingInMovieCode(String movieCode);

    Optional <Movie> findByMovieCodeIgnoreCase(String movieCode);

    List<Movie> findByMovieId(int id);
}
