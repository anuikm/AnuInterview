package com.example.Test.service;

import com.example.Test.entity.Movie;
import com.example.Test.entity.Rating;
import com.example.Test.repository.MovieRepository;
import com.example.Test.repository.RatingRepository;
import com.example.Test.request.MovieRequest;
import com.example.Test.request.MovieResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MovieService {
    private final MovieRepository movieRepository;
    private final RatingRepository ratingRepository;


    public MovieResponse createMovie(MovieRequest request) {
        Movie movie = Movie.builder()
                .movieCode(request.getMovieCode())
                .title(request.getTitle())
                .director(request.getDirector())
                .releaseYear(request.getReleaseYear())
                .build();

        Rating rating = Rating.builder()
                .movie(movie)
                .reviewerName(request.getRating().getReviewerName())
                .score(request.getRating().getScore())
                .reviewText(request.getRating().getReviewText())
                .ratedOn(LocalDateTime.now())
                .build();

        movie.setRatings(List.of(rating));
        movieRepository.save(movie);

        List<MovieResponse.RatingDTO> ratings = movie.getRatings().stream()
                .map(r -> MovieResponse.RatingDTO.builder()
                        .reviewerName(r.getReviewerName())
                        .score(r.getScore())
                        .reviewText(r.getReviewText())
                        .build())
                .collect(Collectors.toList());

        double avg = ratings.stream().mapToInt(MovieResponse.RatingDTO::getScore).average().orElse(0);
        long total = ratings.size();
        Map<Integer, Long> distribution = ratings.stream()
                .collect(Collectors.groupingBy(MovieResponse.RatingDTO::getScore, Collectors.counting()));

        return MovieResponse.builder()
                .movieCode(movie.getMovieCode())
                .title(movie.getTitle())
                .director(movie.getDirector())
                .releaseYear(movie.getReleaseYear())
                .ratings(ratings)
                .averageRating(avg)
                .totalRatings(total)
                .scoreDistribution(distribution)
                .build();
    }

    public MovieResponse getMovieDetails(String movieCode) {
        Movie movie = movieRepository.findByMovieCode(movieCode)
                .orElseThrow(() -> new NoSuchElementException("Movie not found"));

        List<MovieResponse.RatingDTO> ratings = movie.getRatings().stream()
                .map(r -> MovieResponse.RatingDTO.builder()
                        .reviewerName(r.getReviewerName())
                        .score(r.getScore())
                        .reviewText(r.getReviewText())
                        .build())
                .collect(Collectors.toList());

        double avg = ratings.stream().mapToInt(MovieResponse.RatingDTO::getScore).average().orElse(0);
        long total = ratings.size();
        Map<Integer, Long> distribution = ratings.stream()
                .collect(Collectors.groupingBy(MovieResponse.RatingDTO::getScore, Collectors.counting()));

        return MovieResponse.builder()
                .movieCode(movie.getMovieCode())
                .title(movie.getTitle())
                .director(movie.getDirector())
                .releaseYear(movie.getReleaseYear())
                .ratings(ratings)
                .averageRating(avg)
                .totalRatings(total)
                .scoreDistribution(distribution)
                .build();
    }


}
