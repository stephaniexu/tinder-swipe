package com.playground.tinderswipe.service.impl;

import com.playground.tinderswipe.model.Account;
import com.playground.tinderswipe.repository.MatchRepository;
import com.playground.tinderswipe.repository.entity.MatchEntity;
import com.playground.tinderswipe.service.AccountService;
import com.playground.tinderswipe.service.MatchService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MatchServiceImpl implements MatchService {
    private static final Logger logger = LogManager.getLogger();

    private final MatchRepository matchRepository;
    private final AccountService accountService;

    @Autowired
    public MatchServiceImpl(MatchRepository matchRepository,
                            AccountService accountService) {
        this.matchRepository = matchRepository;
        this.accountService = accountService;
    }

    @Override
    public void addMatches(@NotNull String swiper, @NotNull String swipee) {
        MatchEntity swipeeMatch = matchRepository.findOne(swipee);
        if (swipeeMatch == null) {
            swipeeMatch = new MatchEntity(swipee);
        }
        swipeeMatch.addMatch(swiper);
        matchRepository.save(swipeeMatch);

        MatchEntity swiperMatch = matchRepository.findOne(swiper);
        if (swiperMatch == null) {
            swiperMatch = new MatchEntity(swiper);
        }
        swiperMatch.addMatch(swipee);
        matchRepository.save(swiperMatch);

        logger.debug("{} and {} are match now", swiper, swipee);
    }

    @Override
    public List<Account> getMatches(String accountId) {
        MatchEntity matchEntity = matchRepository.findOne(accountId);
        if (matchEntity != null && matchEntity.getMatches() != null) {
            Set<String> matchedAccountIds = matchEntity.getMatches().stream().collect(Collectors.toSet());
            logger.debug("{} has match {}", accountId, matchedAccountIds);
            return accountService.getAccounts(matchedAccountIds);
        }
        return Collections.emptyList();
    }
}
