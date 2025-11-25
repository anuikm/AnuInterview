package com.example.Test.entity;

import lombok.*;

import java.time.LocalDate;


@Data
@Builder
@Getter
public class Rating {
    private int id;
    private String movieCode;
    private String title;
    private String director;
    private String releaseYear = LocalDate.now().toString();
}
