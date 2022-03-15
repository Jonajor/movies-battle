package com.letscome.cardgame.controllers;

import com.letscome.cardgame.domain.dtos.GameDto;
import com.letscome.cardgame.domain.dtos.GameResponseDto;
import com.letscome.cardgame.domain.dtos.ResultGameDto;
import com.letscome.cardgame.domain.exceptions.GameBadRequestException;
import com.letscome.cardgame.domain.exceptions.GameNotFoundException;
import com.letscome.cardgame.domain.exceptions.UnprocessableEntityException;
import com.letscome.cardgame.domain.services.GameService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/game")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GameController {

    private final GameService gameService;

    @PostMapping("/create")
    public ResponseEntity<GameResponseDto> createGame(@Valid @RequestBody GameDto game){
        return ResponseEntity.status(HttpStatus.CREATED).body(gameService.save(game));
    }

    @GetMapping("/result/{movie_id}")
    public ResponseEntity<ResultGameDto> resultGame(@RequestParam("user_id") Long userId,
                                                    @RequestParam("game_id") Long gameId,
                                                    @PathVariable(name = "movie_id") String chosenMovieId){
        try {
            gameService.findByStatus(gameId);
            return ResponseEntity.ok(gameService.resultGame(userId, gameId, chosenMovieId));
        } catch (GameNotFoundException e){
            throw new GameNotFoundException();
        } catch (UnprocessableEntityException e){
            throw new UnprocessableEntityException();
        }
    }

    @GetMapping("/finish/{game_id}")
    public ResponseEntity<GameDto> finishGame(@PathVariable(name = "game_id") Long gameId){
        try {
            return ResponseEntity.ok(gameService.update(gameId));
        } catch (GameNotFoundException e){
            throw new GameNotFoundException();
        } catch (GameBadRequestException e){
            throw new GameBadRequestException();
        }
    }
}
