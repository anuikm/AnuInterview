package com.example.Test.controller;

import com.example.Test.request.MovieRequest;
import com.example.Test.request.MovieResponse;
import com.example.Test.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;
    @PostMapping("/movies")
    public ResponseEntity<MovieResponse> createMovie(@Valid @RequestBody MovieRequest request){
        return ResponseEntity.ok(movieService.createMovie(request));
    }
    @GetMapping("/movies/{movieCode}")
    public ResponseEntity<MovieResponse>  getMovie(@PathVariable String movieCode){
        return ResponseEntity.ok(movieService.getMovieDetails(movieCode));
    }
}
