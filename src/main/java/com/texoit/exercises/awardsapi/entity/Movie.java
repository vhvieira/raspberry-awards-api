package com.texoit.exercises.awardsapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
class Movie {
    @Id
    @GeneratedValue
    private Long id;
    private int year;
    private String title;
    private String studios;
    private String producers;
    private boolean winner;
}