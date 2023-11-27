package com.texoit.exercises.awardsapi.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Movie {
    @Id
    @GeneratedValue
    private Long movieId;
    private int releaseYear;
    private String title;
    private String studios;
    private String producers;
    private boolean winner;
}