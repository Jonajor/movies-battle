package com.letscome.cardgame.domain.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.letscome.cardgame.domain.entities.Movie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieDto {
    private String id;
    private String name;
    @JsonProperty(value = "release_year")
    private Long releaseYear;
    private String certificate;
    private String runtime;
    private String genre;
    private String director;


    public MovieDto entityToDto(Movie movie){
        return MovieDto.builder()
                .id(movie.getId())
                .name(movie.getName())
                .releaseYear(movie.getReleaseYear())
                .certificate(movie.getCertificate())
                .runtime(movie.getRuntime())
                .genre(movie.getGenre())
                .director(movie.getDirector())
                .build();

    }
}
