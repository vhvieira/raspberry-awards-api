package com.texoit.exercises.awardsapi.repository;

import com.texoit.exercises.awardsapi.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByWinnerTrueOrderByYearAsc();
}

