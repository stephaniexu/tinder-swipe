package com.playground.tinderswipe.service;

public interface SwipeService {
    void favorite(String swiper, String swipee);

    void dismiss(String swiper, String swipee);
}
