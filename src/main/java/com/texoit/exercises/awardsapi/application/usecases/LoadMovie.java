package com.texoit.exercises.awardsapi.application.usecases;

import com.texoit.exercises.awardsapi.domain.entity.Movie;
import com.texoit.exercises.awardsapi.infrastructure.persistence.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Usecase class (Clean architecture)
 */
@Slf4j
public class LoadMovie {

    private MovieRepository repository;

    public LoadMovie(MovieRepository repository) {
        this.repository = repository;
    }

    /**
     * Loads a list of movies from a CSV file
     */
    public void loadDataFromCSV(String csvFile) {
        try {
            Resource resource = new ClassPathResource(csvFile);
            BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(";");
                if(fields.length > 3) {
                    Movie movie = new Movie();
                    movie.setReleaseYear(Integer.parseInt(fields[0]));
                    movie.setTitle(fields[1]);
                    movie.setStudios(fields[2]);
                    movie.setProducers(fields[3]);
                    if(fields.length > 4) {
                        movie.setWinner(Boolean.parseBoolean(fields[4]));
                    } else {
                        movie.setWinner(false);
                    }
                    repository.save(movie);
                    log.debug("Movie: {} stored into database.");
                } else {
                    log.warn("Not all required columns found for movie data: {}");
                }
            }
        } catch (Exception ex) {
           log.error("Error while loading movies from CSV file", ex);
        }
    }
}
