package com.example.Test.request;

import com.example.Test.request.RatingRequest;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieRequest {

    @NotBlank
    private String movieCode;

    @NotBlank
    private String title;

    @NotBlank
    private String director;

    private int releaseYear;

    @NotNull
    private RatingRequest rating;
}
