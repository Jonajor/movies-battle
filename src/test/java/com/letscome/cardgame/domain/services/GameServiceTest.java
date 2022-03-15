package com.letscome.cardgame.domain.services;

import com.letscome.cardgame.domain.dtos.GameDto;
import com.letscome.cardgame.domain.entities.Game;
import com.letscome.cardgame.domain.entities.Movie;
import com.letscome.cardgame.domain.enums.StatusEnum;
import com.letscome.cardgame.domain.exceptions.GameBadRequestException;
import com.letscome.cardgame.domain.exceptions.GameNotFoundException;
import com.letscome.cardgame.domain.exceptions.UnprocessableEntityException;
import com.letscome.cardgame.domain.repositories.GameRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class GameServiceTest {

    @InjectMocks
    GameService gameService;

    @Mock
    MovieService movieService;
    @Mock
    GameRepository gameRepository;
    @Mock
    RankingService rankingService;

    Game game = new Game();
    Movie movie = new Movie();

    @BeforeEach
    void setup(){
        game.setCount(1);
        game.setUserId(1L);
        game.setId(1L);
        game.setMovieListId(List.of("niddid"));

        movie.setCertificate("djidjd");
        movie.setDirector("udgdgd");
        movie.setGenre("ihfuhdid");
        movie.setId("niddid");
        movie.setImdbRating(1D);
        movie.setMetaScore("dihdihd");
        movie.setName("sjsijs");


        when(gameRepository.save(any())).thenReturn(game);
        when(gameRepository.findById(any())).thenReturn(Optional.of(game));
        when(gameRepository.findByUserIdAndStatus(any(), any())).thenReturn(Optional.empty());
    }

    @Test
    public void saveTest(){
        Movie movie2 = new Movie();
        movie2.setCertificate("djidjd");
        movie2.setDirector("udgdgd");
        movie2.setGenre("ihfuhdid");
        movie2.setId("niddid");
        movie2.setImdbRating(2D);
        movie2.setMetaScore("dihdihd");
        movie2.setName("sjsijs");

        List<Movie> movieList = new ArrayList<>();
        movieList.add(movie);
        movieList.add(movie2);

        when(movieService.getAllMovies()).thenReturn(movieList);

        var result = gameService.save(GameDto.builder().userId(1L).build());
        assertNotNull(result);
        assertEquals(result.getId(), 1L);
        assertEquals(result.getMovieDtoList().size(), 2);
    }

    @Test
    public void saveGameBadRequestExceptionTest(){
        game.setStatus(StatusEnum.ACTIVE);
        when(gameRepository.findByUserIdAndStatus(any(), any())).thenReturn(Optional.of(game));
        assertThrows(GameBadRequestException.class, () -> {
            gameService.save(GameDto.builder().userId(1L).build());
        });
    }

    @Test
    public void findByStatusTest(){
        gameService.findByStatus(game.getId());
    }

    @Test
    public void findByStatusUnprocessableEntityExceptionTest(){
        game.setStatus(StatusEnum.INACTIVE);
        when(gameRepository.findByIdAndStatus(any(), any())).thenReturn(Optional.of(game));
        assertThrows(UnprocessableEntityException.class, () -> {
            gameService.findByStatus(game.getId());
        });
    }

    @Test
    public void resultGameTest(){
        Movie movie2 = new Movie();
        movie2.setCertificate("djidjd");
        movie2.setDirector("udgdgd");
        movie2.setGenre("ihfuhdid");
        movie2.setId("niddid");
        movie2.setImdbRating(2D);
        movie2.setMetaScore("dihdihd");
        movie2.setName("sjsijs");

        List<Movie> movieList = new ArrayList<>();
        movieList.add(movie);
        movieList.add(movie2);

        when(movieService.getListMovies(any())).thenReturn(movieList);
        var result = gameService.resultGame(game.getUserId(), game.getId(), movie2.getId());
        assertNotNull(result);
        assertEquals(result.getMessage(), "Errou");
    }

    @Test
    public void resultGameException(){
        when(gameRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(GameNotFoundException.class, () -> {
            gameService.resultGame(game.getUserId(), game.getId(), movie.getId());
        });
    }

    @Test
    public void updateTest(){
        var result = gameService.update(game.getId());
        assertNotNull(result);
        assertEquals(result.getUserId(), 1);
    }

    @Test
    public void updateExceptionTest(){
        when(gameRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(GameNotFoundException.class, () -> {
            gameService.update(game.getId());
        });
    }
}
