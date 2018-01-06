package com.playground.tinderswipe.controller;

import com.playground.tinderswipe.model.Account;
import com.playground.tinderswipe.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/match")
@RestController
public class MatchController {
    private final MatchService matchService;

    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/{accountId}")
    public List<Account> getRecommendations(@PathVariable String accountId) {
        return matchService.getMatches(accountId);
    }
}
