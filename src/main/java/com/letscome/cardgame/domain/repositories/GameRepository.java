package com.letscome.cardgame.domain.repositories;

import com.letscome.cardgame.domain.entities.Game;
import com.letscome.cardgame.domain.enums.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface GameRepository extends JpaRepository<Game, Long> {
    Optional<Game> findByIdAndStatus(Long id, StatusEnum statusEnum);
    Optional<Game> findByUserIdAndStatus(Long userId, StatusEnum statusEnum);
}
