package com.texoit.exercises.awardsapi.infrastructure.persistence;

import com.texoit.exercises.awardsapi.domain.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByWinnerTrueOrderByReleaseYearAsc();
}

