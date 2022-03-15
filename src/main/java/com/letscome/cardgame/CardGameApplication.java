package com.letscome.cardgame;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.letscome.cardgame.domain.entities.Movie;
import com.letscome.cardgame.domain.services.MovieService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class CardGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardGameApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(MovieService movieService){
		return args -> {
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<Movie>> typeReference = new TypeReference<>() {};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/movies.json");
			try {
				List<Movie> movies = mapper.readValue(inputStream,typeReference);
				movieService.save(movies);
				System.out.println("Movies Saved!");
			} catch (IOException e){
				System.out.println("Unable to save movies: " + e.getMessage());
			}
		};
	}

}
