package com.playground.tinderswipe.controller;

import com.playground.tinderswipe.model.Swipe;
import com.playground.tinderswipe.service.SwipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/swipe")
@RestController
public class SwipeController {
    private final SwipeService swipeService;

    @Autowired
    public SwipeController(SwipeService swipeService) {
        this.swipeService = swipeService;
    }

    @PostMapping("/favorite")
    public void favorite(@RequestBody Swipe swipe){
        swipeService.favorite(swipe.getSwiper(), swipe.getSwipee());
    }

    @PostMapping("/dismiss")
    public void dismiss(@RequestBody Swipe swipe){
        swipeService.dismiss(swipe.getSwiper(), swipe.getSwipee());
    }
}
