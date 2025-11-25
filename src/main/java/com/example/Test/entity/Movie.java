package com.example.Test.entity;

import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    private int id;
    private String movieId;
    private String reviewerName;
    @Min(1)
    @Max(10)
    private int score;
    private String reviewText;
    private String ratedOn;
    @OneToOne
    private Rating rating;
}
