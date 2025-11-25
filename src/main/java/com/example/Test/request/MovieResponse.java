package com.example.Test.request;

import jakarta.validation.constraints.NotNull;

public class MovieResponse {
    private int id;
    private String movieId;
    private String reviewerName;
    private int score;
    private String reviewText;
    private String ratedOn;
    private RatingRequest ratingRequest;
}
