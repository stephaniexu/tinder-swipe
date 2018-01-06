package com.playground.tinderswipe.controller;

import com.playground.tinderswipe.model.Account;
import com.playground.tinderswipe.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/recommendation")
@RestController
public class RecommendationController {
    private final RecommendationService recommendationService;

    @Autowired
    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @GetMapping("/{accountId}")
    public List<Account> getRecommendations(@PathVariable String accountId) {
        return recommendationService.getRecommendations(accountId);
    }

}
