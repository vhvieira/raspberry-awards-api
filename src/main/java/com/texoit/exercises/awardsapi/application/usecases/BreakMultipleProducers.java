package com.texoit.exercises.awardsapi.application.usecases;

import com.texoit.exercises.awardsapi.application.services.MovieService;
import com.texoit.exercises.awardsapi.domain.entity.Movie;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Sometimes there are more than one producer in the name of movie
 * This use case breaks the movie list and create a record for each producer
 */
@Slf4j
public class BreakMultipleProducers {

    private java.util.List<Movie> allMovies;

    public BreakMultipleProducers(java.util.List<Movie> allMovies) {
        this.allMovies = allMovies;
    }

    public Map<String, java.util.List<Movie>> retrieveAllProducerMap() {
        Map<String, java.util.List<Movie>> finalMap = new HashMap<>();
        // Iterar sobre as chaves do Map:
        for (Movie movie : allMovies) {
            String producerNames = movie.getProducers();
            if (producerNames.contains("and")) {
                String[] producers = producerNames.split("and");
                log.debug("producerNames: {} were break into multiple names: {}", producerNames, producers);
                for (int i = 0; i < producers.length; i++) {
                    String currentProducerName = producers[i].trim();
                    this.processProducerMovies(finalMap, currentProducerName, movie);
                }
            } else {
                log.debug("producerNames: {} did NOT break into multiple names:", producerNames);
                this.processProducerMovies(finalMap, producerNames, movie);
            }
        }
        return finalMap;
    }

    /**
     * Adding list to final map per individual producer name
     * @param finalMap
     * @param producerName
     * @param movie
     */
    private void processProducerMovies(Map<String, List<Movie>> finalMap, String producerName, Movie movie) {
        if (finalMap.containsKey(producerName)) {
            log.debug("Already found some movies form producer: {}", producerName);
            finalMap.get(producerName).add(movie);
        } else {
            log.debug("Not yet found some movies form producer: {}", producerName);
            List<Movie> producerMovies = new ArrayList<>();
            producerMovies.add(movie);
            finalMap.put(producerName, producerMovies);
        }
    }
}
