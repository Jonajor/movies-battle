package com.letscome.cardgame.domain.services;

import com.letscome.cardgame.domain.dtos.GameDto;
import com.letscome.cardgame.domain.dtos.GameResponseDto;
import com.letscome.cardgame.domain.dtos.MovieDto;
import com.letscome.cardgame.domain.dtos.ResultGameDto;
import com.letscome.cardgame.domain.entities.Game;
import com.letscome.cardgame.domain.entities.Movie;
import com.letscome.cardgame.domain.enums.StatusEnum;
import com.letscome.cardgame.domain.exceptions.GameBadRequestException;
import com.letscome.cardgame.domain.exceptions.GameNotFoundException;
import com.letscome.cardgame.domain.exceptions.LimitRequestException;
import com.letscome.cardgame.domain.exceptions.UnprocessableEntityException;
import com.letscome.cardgame.domain.repositories.GameRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GameService {

    private final MovieService movieService;
    private final GameRepository gameRepository;
    private final RankingService rankingService;

    public GameResponseDto save(GameDto game){
        var gameStatus= gameRepository.findByUserIdAndStatus(game.getUserId(), StatusEnum.ACTIVE);
        if (gameStatus.isPresent()){
            throw new GameBadRequestException();
        }

        Random rand = new Random();
        var randomElement = new MovieDto();
        var givenList = movieService.getAllMovies();
        int numberOfElements = 2;
        return extractedMovie(game, rand, randomElement, givenList, numberOfElements);

    }

    private GameResponseDto extractedMovie(GameDto game, Random rand, MovieDto randomElement, List<Movie> givenList, int numberOfElements) {
        List<MovieDto> movieList = new ArrayList<>();
        var gameEntity = new Game();
        List<String> idMovieString = new ArrayList<>();
        for (int i = 0; i < numberOfElements; i++) {
            int randomIndex = rand.nextInt(givenList.size());
            var movieEntity = givenList.get(randomIndex);
            var movie = randomElement.entityToDto(movieEntity);
            givenList.remove(randomIndex);
            idMovieString.add(movieEntity.getId());
            movieList.add(movie);
        }
        gameEntity.setUserId(game.getUserId());
        gameEntity.setMovieListId(idMovieString);
        gameEntity.setStatus(StatusEnum.ACTIVE);
        gameEntity.setCount(0);
        var gameSave = gameRepository.save(gameEntity);
        return new GameResponseDto(gameSave.getId(), movieList);
    }

    public void findByStatus(Long gameId){
        var game = gameRepository.findByIdAndStatus(gameId, StatusEnum.INACTIVE);
        if (game.isPresent())
            throw new UnprocessableEntityException();
    }

    public ResultGameDto resultGame(Long userId, Long gameId, String chosenMovieId) {

        return gameRepository.findById(gameId)
                .map(game -> getResult(game, userId, chosenMovieId, movieService.getListMovies(game.getMovieListId())))
                .orElseThrow(GameNotFoundException::new);
    }

    private ResultGameDto getResult(Game game, Long userId, String chosenMovieId, List<Movie> movies) {
        ResultGameDto result = new ResultGameDto();
        if (game.getCount() == 3){
            game.setStatus(StatusEnum.INACTIVE);
            gameRepository.save(game);
            throw new LimitRequestException();
        }

        if (movies.get(0).getImdbRating() < movies.get(1).getImdbRating()){
            if (movies.get(0).getId().equals(chosenMovieId)){
                rankingService.verifyIfExist(userId, 0);
                game.setCount(game.getCount() + 1);
                gameRepository.save(game);
                result.setMessage("Errou");
                return result;
            }
        }
        if (movies.get(0).getImdbRating() > movies.get(1).getImdbRating()){
            if (movies.get(1).getId().equals(chosenMovieId)){
                rankingService.verifyIfExist(userId, 0);
                game.setCount(game.getCount() + 1);
                gameRepository.save(game);
                result.setMessage("Errou");
                return result;
            }
        }
        if (movies.get(0).getImdbRating() < movies.get(1).getImdbRating()){
            if (movies.get(1).getId().equals(chosenMovieId)){
                rankingService.verifyIfExist(userId, 1);
                game.setStatus(StatusEnum.INACTIVE);
                game.setCount(game.getCount() + 1);
                gameRepository.save(game);
                result.setMessage("Acertou");
                return result;
            }
        }
        if (movies.get(0).getImdbRating() > movies.get(1).getImdbRating()){
            if (movies.get(0).getId().equals(chosenMovieId)){
                rankingService.verifyIfExist(userId, 1);
                game.setStatus(StatusEnum.INACTIVE);
                game.setCount(game.getCount() + 1);
                gameRepository.save(game);
                result.setMessage("Acertou");
                return result;
            }
        }
        game.setStatus(StatusEnum.INACTIVE);
        game.setCount(game.getCount() + 1);
        gameRepository.save(game);
        result.setMessage("Os dos possuem a mesma pontuacao");
        return result;
    }

    public GameDto update(Long gameId) {
        return gameRepository.findById(gameId)
                .map(this::updateGame)
                .orElseThrow(GameNotFoundException::new);
    }

    public GameDto updateGame(Game game){
        game.setStatus(StatusEnum.INACTIVE);
        var games = gameRepository.save(game);
        return GameDto.builder().userId(games.getUserId()).build();
    }

}
