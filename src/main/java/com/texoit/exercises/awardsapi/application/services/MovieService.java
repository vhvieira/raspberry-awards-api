package com.texoit.exercises.awardsapi.application.services;

import com.texoit.exercises.awardsapi.domain.entity.Movie;
import com.texoit.exercises.awardsapi.infrastructure.persistence.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    public List<Movie> findAll() {
        return repository.findAll();
    }

    public Movie findById(Long id) {
        Movie movie = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid movie id: " + id));
        return movie;
    }


    public Movie save(Movie movie) {
        return repository.save(movie);
    }

    public Movie update(Long id, Movie movieData) {
        Movie movie = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid movie id: " + id));
        movie.setTitle(movieData.getTitle());
        movie.setReleaseYear(movieData.getReleaseYear());
        movie.setStudios(movieData.getStudios());
        movie.setProducers(movieData.getProducers());
        movie.setWinner(movieData.isWinner());
        Movie updatedMovie = repository.save(movie);
        return updatedMovie;
    }

    public void deleteMovie(Long id) {
        Movie filme = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid movie id: " + id));
        repository.delete(filme);
    }

    public Map<String, List<Movie>> getFilmesGroupedByStudios() {
        List<Movie> filmes = repository.findAll();
        return filmes.stream().collect(Collectors.groupingBy(Movie::getStudios));
    }

    public Map<String, List<Movie>> getFilmesGroupedByProducers() {
        List<Movie> filmes = repository.findAll();
        return filmes.stream().collect(Collectors.groupingBy(Movie::getProducers));
    }
}