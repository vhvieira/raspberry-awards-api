package com.texoit.exercises.awardsapi.interfaces.controllers;

import com.texoit.exercises.awardsapi.application.services.MovieService;
import com.texoit.exercises.awardsapi.domain.entity.Movie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class was create to demonstrate a classical architecture
 * Controller > Service > Repository
 * Also demonstrates a RESTful level 2 maturity from Richardson
 * Not required by challenge but useful for local testing
 */
@RestController
@RequestMapping("/movies")
@Slf4j
public class MovieController {

    @Autowired
    private MovieService service;

    @GetMapping
    public List<Movie> listAll() {
        return service.findAll();
    }

    @PostMapping
    public Movie createFilme(@RequestBody Movie movie) {
        return service.save(movie);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> retrieveMovie(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.findById(id));
        } catch (IllegalArgumentException argumentException) {
            log.warn("Filme não encontrado com id: {}", id);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateFilme(@PathVariable Long id, @RequestBody Movie movieData) {
        try {
            Movie response = service.update(id, movieData);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException argumentException) {
            log.warn("Filme não encontrado com id: {}", id);
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilme(@PathVariable Long id) {
        try {
            service.deleteMovie(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException argumentException) {
            log.warn("Filme não encontrado com id: {}", id);
            return ResponseEntity.badRequest().build();
        }
    }
}

