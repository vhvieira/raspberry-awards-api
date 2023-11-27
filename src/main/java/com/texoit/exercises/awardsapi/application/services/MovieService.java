package com.texoit.exercises.awardsapi.application.services;

import com.texoit.exercises.awardsapi.domain.entity.Movie;
import com.texoit.exercises.awardsapi.infrastructure.persistence.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepository repository;

    @Autowired
    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }

    public Map<String, List<Movie>> getFilmesGroupedByProducers() {
        List<Movie> filmes = repository.findAll();
        return filmes.stream().collect(Collectors.groupingBy(Movie::getProducers));
    }
}