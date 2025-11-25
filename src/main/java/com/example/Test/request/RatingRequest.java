package com.example.Test.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RatingRequest {
    private int id;
    private String movieCode;
    private String title;
    private String director;
    private String releaseYear;
    private RatingRequest ratingRequest;
}
