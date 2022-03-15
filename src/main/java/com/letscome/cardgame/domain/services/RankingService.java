package com.letscome.cardgame.domain.services;

import com.letscome.cardgame.domain.dtos.RankingDto;
import com.letscome.cardgame.domain.entities.Ranking;
import com.letscome.cardgame.domain.repositories.RankingRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RankingService {

    private final RankingRepository rankingRepository;

     public List<RankingDto> listAll(){
         return rankingRepository.findAllByOrderByPointAsc()
                 .stream()
                 .map(ranking -> new RankingDto().entityToDto(ranking))
                 .collect(Collectors.toList());
     }

    public void verifyIfExist(Long userId, int point){
         rankingRepository.findById(userId)
                 .ifPresentOrElse(ranking1 -> save(userId, point, ranking1.getPoint()),
                         () -> save(userId, point, 0));
    }

    private void save(Long userId, int point, int rankingPoint) {
        var ranking = Ranking.builder()
                .userId(userId)
                .point(rankingPoint + point)
                .build();
        rankingRepository.save(ranking);
    }


}
