package com.texoit.exercises.awardsapi.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_generator")
    @SequenceGenerator(name = "movie_generator", sequenceName = "MOVIE_SEQ", allocationSize = 1)
    private Long movieId;
    private Long releaseYear;
    private String title;
    private String studios;
    private String producers;
    private boolean winner;
}