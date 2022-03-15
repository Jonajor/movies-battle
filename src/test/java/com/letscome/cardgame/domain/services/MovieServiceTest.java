package com.letscome.cardgame.domain.services;

import com.letscome.cardgame.domain.entities.Movie;
import com.letscome.cardgame.domain.repositories.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class MovieServiceTest {

    @InjectMocks
    MovieService movieService;

    @Mock
    MovieRepository movieRepository;

    Movie movie = new Movie();

    @BeforeEach
    public void setup(){
        movie.setCertificate("djidjd");
        movie.setDirector("udgdgd");
        movie.setGenre("ihfuhdid");
        movie.setId("niddid");
        movie.setImdbRating(1D);
        movie.setMetaScore("dihdihd");
        movie.setName("sjsijs");

        when(movieRepository.findAll()).thenReturn(List.of(movie));
        when(movieRepository.findAllById(any())).thenReturn(List.of(movie));
        when(movieRepository.save(any())).thenReturn(movie);
    }

    @Test
    public void getAllMoviesTest(){
        var result = movieService.getAllMovies();
        assertNotNull(result);
        assertEquals(result.size(), 1);
    }

    @Test
    public void findAllByIdTest(){
        var result = movieService.getListMovies(List.of(movie.getId()));
        assertNotNull(result);
        assertEquals(result.size(), 1);
    }

    @Test
    public void saveTest(){
      movieService.save(List.of(movie));
    }
}
