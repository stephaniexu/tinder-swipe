package com.playground.tinderswipe.service.impl;

import com.playground.tinderswipe.model.Account;
import com.playground.tinderswipe.repository.DismissRepository;
import com.playground.tinderswipe.repository.FavoriteRepository;
import com.playground.tinderswipe.repository.entity.DismissEntity;
import com.playground.tinderswipe.repository.entity.FavoriteEntity;
import com.playground.tinderswipe.service.AccountService;
import com.playground.tinderswipe.service.RecommendationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RecommendationServiceImpl implements RecommendationService{
    private static final Logger logger = LogManager.getLogger();

    private final FavoriteRepository favoriteRepository;
    private final DismissRepository dismissRepository;
    private final AccountService accountService;

    @Autowired
    public RecommendationServiceImpl(FavoriteRepository favoriteRepository,
                                     DismissRepository dismissRepository,
                                     AccountService accountService) {
        this.favoriteRepository = favoriteRepository;
        this.dismissRepository = dismissRepository;
        this.accountService = accountService;
    }

    @Override
    public List<Account> getRecommendations(@NotNull String accountId) {
        // TODO get recommended account ids
        // use all accounts for now
        List<String> recommendedAccountIds = getRecommendedAccountIds(accountId);

        // get favorite account ids
        Set<String> favorites = favoriteRepository.findBySwiper(accountId).stream()
                .map(FavoriteEntity::getSwipee)
                .collect(Collectors.toSet());
        logger.debug("favorites for {}:{}", accountId, favorites);

        // get dismissed account ids
        Set<String> dismisses = dismissRepository.findBySwiper(accountId).stream()
                .map(DismissEntity::getSwipee)
                .collect(Collectors.toSet());
        logger.debug("dismisses for {}:{}", accountId, dismisses);

        List<String> filterdIds = recommendedAccountIds.stream()
                .filter(id -> !favorites.contains(id) && !dismisses.contains(id) && !id.equals(accountId))
                .collect(Collectors.toList());
        logger.debug("filteredIds:{}", filterdIds);

        return accountService.getAccounts(filterdIds);
    }

    private List<String> getRecommendedAccountIds(String accountId) {
        return accountService.getAllAccounts().stream().map(Account::getId).collect(Collectors.toList());
    }
}
