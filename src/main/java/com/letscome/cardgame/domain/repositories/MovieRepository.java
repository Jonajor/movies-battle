package com.letscome.cardgame.domain.repositories;

import com.letscome.cardgame.domain.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, String> { }
