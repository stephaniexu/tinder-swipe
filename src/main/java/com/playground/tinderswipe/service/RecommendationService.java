package com.playground.tinderswipe.service;

import com.playground.tinderswipe.model.Account;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface RecommendationService {
    List<Account> getRecommendations(@NotNull String accountId);
}
