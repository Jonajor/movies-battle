package com.letscome.cardgame.domain.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class GameResponseDto {
    private Long id;
    @JsonProperty(value = "movie_list")
    private List<MovieDto> movieDtoList;

    public GameResponseDto(Long id, List<MovieDto> movieDtoList) {
        this.id = id;
        this.movieDtoList = movieDtoList;
    }
}
