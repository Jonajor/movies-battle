package com.letscome.cardgame.domain.services;

import com.letscome.cardgame.domain.entities.Movie;
import com.letscome.cardgame.domain.repositories.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MovieService {

    private final MovieRepository movieRepository;

    public void save(List<Movie> movies){
        for(Movie movie: movies) {
            movieRepository.save(movie);
        }
    }

    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    public  List<Movie> getListMovies(List<String> movieListId){
        return movieRepository.findAllById(movieListId);
    }
}
