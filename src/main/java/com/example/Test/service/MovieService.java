package com.example.Test.service;

import com.example.Test.entity.Movie;
import com.example.Test.entity.Rating;
import com.example.Test.repository.MovieRepository;
import com.example.Test.repository.RatingRepository;
import com.example.Test.request.MovieRequest;
import com.example.Test.request.MovieResponse;
import com.example.Test.request.RatingRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@NoArgsConstructor(force = true)
public class MovieService {
    private final MovieRepository movieRepository;
    private final RatingRepository ratingRepository;
    public Movie createMovie(MovieRequest movieRequest){
        RatingRequest request = movieRequest.getRatingRequest();
                Rating newRating =Rating.builder()
                .id(request.getId())
                        .movieCode(request.getMovieCode())
                        .director(request.getDirector())
                        .releaseYear(request.getReleaseYear())
                        .title(request.getTitle())
                        .build();
                Rating savedRating = ratingRepository.save(newRating);
        Movie newMovie = new Movie();
        newMovie.builder().movieId(movieRequest.getMovieId())
                .id(movieRequest.getId())
                .reviewerName(movieRequest.getReviewerName())
                .score(movieRequest.getScore())
                .ratedOn(movieRequest.getRatedOn())
                .reviewText(movieRequest.getReviewText())
                .rating(savedRating)
                .build();
        return movieRepository.save(newMovie);
    }

    public Map<String, Object> getMovieDetails(String movieCode) {
        Movie movie = movieRepository.findByMovieCodeIgnoreCase(movieCode)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        List <Rating> ratings =  ratingRepository.findAll();

        List<Movie> movies = movieRepository.findByMovieId(movie.getId());
        List<String> reviews= movies.stream()
                .map(m -> m.getReviewText())
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        double avgRating = movies.stream()
                .mapToInt(Movie::getScore)
                .average()
                .orElse(0.0);
        Map<Integer, Long> ratingCount = new HashMap<>();

        for (int i = 1; i <= 10; i++) {
            final int rating = i;
            ratingCount.put(i, movies.stream().filter(r -> r.getScore() == rating).count());
        }
        return Map.of(
                "movie", movie,
                "Ratings", reviews,
                "averageRating", avgRating,
                "totalRating", ratings.size(),
                "ScoreDistribution", ratingCount
        );
    }

}
