package com.texoit.exercises.awardsapi.application.usecases;

import com.texoit.exercises.awardsapi.application.services.MovieService;
import com.texoit.exercises.awardsapi.domain.entity.Movie;
import com.texoit.exercises.awardsapi.domain.valueobjects.ProducerInterval;
import com.texoit.exercises.awardsapi.domain.valueobjects.ProducersAwards;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Usecase class (Clean architecture)
 */
@Slf4j
public class AwardsInterval {

    private MovieService service;

    public AwardsInterval(MovieService service) {
        this.service = service;
    }

    /**
     * Logic that returns mininal and maximum interval per producers
     * @return
     */
    public ProducersAwards findByProducers() {
        ProducersAwards awards = new ProducersAwards();
        Map<String, List<Movie>>  allMovies = service.getFilmesGroupedByProducers();
        processAwardsIntervals(awards, allMovies);

        return awards;
    }

    /**
     * Logic that returns mininal and maximum interval per producers
     * @return
     */
    public ProducersAwards findByStudios() {
        ProducersAwards awards = new ProducersAwards();
        Map<String, List<Movie>>  allMovies = service.getFilmesGroupedByStudios();
        processAwardsIntervals(awards, allMovies);

        return awards;
    }



    /**
     * All business logic to group by minimal and maximum awarding intervals
     * @param awards
     * @param allMovies
     */
    private void processAwardsIntervals(ProducersAwards awards, Map<String, List<Movie>> allMovies) {
        // Iterar sobre as chaves do Map:
        for (String producer : allMovies.keySet()) {
            log.debug("Processing movie intervals for producer: {}", producer);
            //for comparing values
            long minimalInterval = Long.MAX_VALUE;
            long maximumInterval = 0L;
            long lastAward = 0L;
            ProducerInterval minInterval = new ProducerInterval();
            ProducerInterval maxInterval = new ProducerInterval();
            List<Movie> producerList = this.sortAndFilterList(producer, allMovies.get(producer));
            log.debug("Sorted list for producer: {} is {}", producer, producerList);
            if(CollectionUtils.isEmpty(producerList)) {
                log.debug("Producer: {} has no winner movie, skipping it.");
                continue;
            }
            for (Movie movie: producerList) {
                log.debug("Interating over movie: {} for producer: {}", movie, producer);
                if(lastAward > 0) {
                    long currentInterval = movie.getReleaseYear() - lastAward;
                    log.debug("Comparing currentInterval: {} with max: {}", currentInterval, maximumInterval);
                    if(currentInterval > maximumInterval) {
                        maxInterval = createProducerInterval(producer, lastAward, currentInterval, movie);
                    }
                    log.debug("Comparing currentInterval: {} with min: {}", currentInterval, minimalInterval);
                    if(currentInterval < minimalInterval) {
                        minInterval = createProducerInterval(producer, lastAward, currentInterval, movie);
                    }
                    lastAward = movie.getReleaseYear();
                } else {
                    lastAward = movie.getReleaseYear();
                    continue;
                }
            }
            log.debug("Adding minInterval: {} and maxInterval: {} to result");
            if(minInterval != null && StringUtils.hasText(minInterval.getProducer())) {
                awards.getMin().add(minInterval);
            }
            if(maxInterval != null && StringUtils.hasText(maxInterval.getProducer())) {
                awards.getMax().add(maxInterval);
            }
        }
    }

    /**
     * Create the correct object for Awards Interval
     * @param producer
     * @param lastAward
     * @param currentInterval
     * @param movie
     * @return
     */
    private ProducerInterval createProducerInterval(String producer, long lastAward, long currentInterval, Movie movie) {
        return ProducerInterval.builder().producer(producer).interval(currentInterval).
                previousWin(lastAward).followingWin(movie.getReleaseYear()).build();
    }

    /**
     * Sorting and filtering is part of the use case, should not be handled in queries or database specific commands.
     * @param list
     * @return
     */
    public List<Movie> sortAndFilterList(String producer, List<Movie> list) {
        //breaking in multiple lines to log all list without winners
        List<Movie> winnerList = list.stream().filter(Movie::isWinner).collect(Collectors.toList());;
        if(!CollectionUtils.isEmpty(winnerList)) {
                return winnerList.stream().sorted(Comparator.comparingLong(Movie::getReleaseYear))
                    .collect(Collectors.toList());
        } else {
            log.debug("No winner found for list. {} for producer: {}", list, producer);
            return null;
        }
    }
}
