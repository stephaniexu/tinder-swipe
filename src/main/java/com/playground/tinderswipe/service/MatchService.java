package com.playground.tinderswipe.service;

import com.playground.tinderswipe.model.Account;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface MatchService {
    void addMatches(@NotNull String swiper, @NotNull String swipee);

    List<Account> getMatches(@NotNull String accoutId);
}
