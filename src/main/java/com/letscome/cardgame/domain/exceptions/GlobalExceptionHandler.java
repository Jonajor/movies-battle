package com.letscome.cardgame.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<Object> handleInternalServerErrorException(
            InternalServerErrorException internalServerErrorException){
        return new ResponseEntity("System failure, please try again later", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<Object> handleUnprocessableEntityException(
            UnprocessableEntityException unprocessableEntityException){
        return new ResponseEntity("it was not possible to process the request", HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(GameNotFoundException.class)
    public ResponseEntity<Object> handleNotFoundExceptionException(
            GameNotFoundException notFoundException){
        return new ResponseEntity("Game does not exist in the system", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundExceptionException(
            UserNotFoundException notFoundException){
        return new ResponseEntity("User does not exist in the system", HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(GameBadRequestException.class)
    public ResponseEntity<Object> handleBadRequestException(
            GameBadRequestException gameBadRequestException){
        return new ResponseEntity("You have an active game, deactivate it to be able to create a new one", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LimitRequestException.class)
    public ResponseEntity<Object> handleLimitException(
            LimitRequestException limitRequestException){
        return new ResponseEntity("You exceeded the limit of attempts", HttpStatus.UNPROCESSABLE_ENTITY);
    }

}