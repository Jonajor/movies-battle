package com.letscome.cardgame.controllers;

import com.letscome.cardgame.domain.dtos.RankingDto;
import com.letscome.cardgame.domain.services.RankingService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ranking")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RankingController {

    private final RankingService rankingService;

    @GetMapping
    public ResponseEntity<List<RankingDto>> resultGame(){
        return ResponseEntity.ok(rankingService.listAll());
    }
}
