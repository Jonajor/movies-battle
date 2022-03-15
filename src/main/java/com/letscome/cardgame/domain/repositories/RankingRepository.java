package com.letscome.cardgame.domain.repositories;

import com.letscome.cardgame.domain.entities.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RankingRepository extends JpaRepository<Ranking, Long> {
    List<Ranking> findAllByOrderByPointAsc();
}
