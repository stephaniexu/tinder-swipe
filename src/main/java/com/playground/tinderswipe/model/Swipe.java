package com.playground.tinderswipe.model;

public class Swipe {
    private String swiper;
    private String swipee;

    public Swipe() {}

    public String getSwiper() {
        return swiper;
    }

    public void setSwiper(String swiper) {
        this.swiper = swiper;
    }

    public String getSwipee() {
        return swipee;
    }

    public void setSwipee(String swipee) {
        this.swipee = swipee;
    }

    @Override
    public String toString() {
        return "Swipe{" +
                "swiper='" + swiper + '\'' +
                ", swipee='" + swipee + '\'' +
                '}';
    }
}
