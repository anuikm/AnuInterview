package com.example.Test.controller;

import com.example.Test.request.MovieRequest;
import com.example.Test.service.MovieService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class MovieController {
    private MovieService movieService;
    @PostMapping("/movies")
    public Object createMovie(@Valid @RequestBody MovieRequest request){
        return movieService.createMovie(request);
    }
    @GetMapping("/movies/{movieCode}")
    public Object getMovie(@PathVariable String movieCode){
        return movieService.getMovieDetails(movieCode);
    }
}
