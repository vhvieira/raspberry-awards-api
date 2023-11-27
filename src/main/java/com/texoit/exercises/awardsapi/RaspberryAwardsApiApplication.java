package com.texoit.exercises.awardsapi;

import com.texoit.exercises.awardsapi.application.usecases.LoadMovie;
import com.texoit.exercises.awardsapi.infrastructure.persistence.MovieRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class RaspberryAwardsApiApplication {

	private final MovieRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(RaspberryAwardsApiApplication.class, args);
	}

	@PostConstruct
	private void loadMovieDataBase() {
		//usecase class (No framework dependency)
		LoadMovie loadMovie = new LoadMovie(repository);
		loadMovie.loadDataFromCSV("movielist.csv");
	}

}
