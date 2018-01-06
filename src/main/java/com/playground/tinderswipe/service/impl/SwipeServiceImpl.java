package com.playground.tinderswipe.service.impl;

import com.playground.tinderswipe.repository.DismissRepository;
import com.playground.tinderswipe.repository.FavoriteRepository;
import com.playground.tinderswipe.repository.entity.DismissEntity;
import com.playground.tinderswipe.repository.entity.FavoriteEntity;
import com.playground.tinderswipe.service.MatchService;
import com.playground.tinderswipe.service.SwipeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SwipeServiceImpl implements SwipeService{
    private final Logger logger = LogManager.getLogger();

    private final FavoriteRepository favoriteRepository;
    private final DismissRepository dismissRepository;
    private final MatchService matchService;

    @Autowired
    public SwipeServiceImpl(FavoriteRepository favoriteRepository,
                            DismissRepository dismissRepository,
                            MatchService matchService) {
        this.favoriteRepository = favoriteRepository;
        this.dismissRepository = dismissRepository;
        this.matchService = matchService;
    }

    @Override
    public void favorite(@NotNull String swiper, @NotNull String swipee) {
        logger.debug("{} favorite {}", swiper, swipee);

        favoriteRepository.save(new FavoriteEntity(swiper, swipee));

        // check if we need to add a match
        // get favorite account ids for swipee
        Set<String> favoritesBySwipee = favoriteRepository.findBySwiper(swipee).stream()
                .map(FavoriteEntity::getSwipee)
                .collect(Collectors.toSet());
        logger.debug("swipee {} favorites:{}", swipee, favoritesBySwipee);

        if (favoritesBySwipee.contains(swiper)) {
            logger.debug("{} has favorited {}, add a match", swipee, swiper);
            // swiper has been favorited by swipee already
            matchService.addMatches(swiper, swipee);
        }

        logger.debug("{} favorited {}", swiper, swipee);
    }

    @Override
    public void dismiss(@NotNull String swiper, @NotNull String swipee) {
        dismissRepository.save(new DismissEntity(swiper, swipee));
        logger.debug("{} dismissed {}", swiper, swipee);
    }
}
