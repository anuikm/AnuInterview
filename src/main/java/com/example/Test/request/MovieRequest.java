package com.example.Test.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder@AllArgsConstructor
@NoArgsConstructor
public class MovieRequest {
    private int id;
    private String movieId;
    private String reviewerName;
    private int score;
    private String reviewText;
    private String ratedOn;
    private RatingRequest ratingRequest;
}
