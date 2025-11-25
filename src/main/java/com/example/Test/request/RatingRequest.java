package com.example.Test.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RatingRequest {

    @NotBlank
    private String reviewerName;

    @Min(1)
    @Max(10)
    private int score;

    private String reviewText;
}
