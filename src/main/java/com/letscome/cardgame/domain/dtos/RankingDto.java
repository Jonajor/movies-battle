package com.letscome.cardgame.domain.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.letscome.cardgame.domain.entities.Movie;
import com.letscome.cardgame.domain.entities.Ranking;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RankingDto {

    @JsonProperty(value = "user_id")
    private Long userId;
    private int point;

    public RankingDto entityToDto(Ranking ranking){
        return RankingDto.builder()
                .userId(ranking.getUserId())
                .point(ranking.getPoint())
                .build();
    }
}
