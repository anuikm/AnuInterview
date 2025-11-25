package com.example.Test.request;

import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieResponse {

    private String movieCode;
    private String title;
    private String director;
    private int releaseYear;

    private List<RatingDTO> ratings;
    private double averageRating;
    private long totalRatings;
    private Map<Integer, Long> scoreDistribution;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class RatingDTO {
        private String reviewerName;
        private int score;
        private String reviewText;
    }
}
