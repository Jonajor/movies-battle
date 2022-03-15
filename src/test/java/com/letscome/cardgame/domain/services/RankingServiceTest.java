package com.letscome.cardgame.domain.services;

import com.letscome.cardgame.domain.dtos.RankingDto;
import com.letscome.cardgame.domain.entities.Ranking;
import com.letscome.cardgame.domain.repositories.RankingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class RankingServiceTest {

    @InjectMocks
    RankingService rankingService;

    @Mock
    RankingRepository rankingRepository;

    Ranking ranking = new Ranking();
    @BeforeEach
    public void setup(){
        ranking.setPoint(1);
        ranking.setNumberOfgames(3);
        ranking.setUserId(1L);

        when(rankingRepository.findAllByOrderByPointAsc()).thenReturn(List.of(ranking));
        when(rankingRepository.save(any())).thenReturn(ranking);
        when(rankingRepository.findById(any())).thenReturn(Optional.of(ranking));
    }

    @Test
    public void listAllTest(){
        var result = rankingService.listAll();
        assertNotNull(result);
        assertEquals(result.stream().findFirst().get().getUserId(), 1);
    }

    @Test
    public void verifyIfExistTest(){
        rankingService.verifyIfExist(ranking.getUserId(), ranking.getPoint());
    }
}
