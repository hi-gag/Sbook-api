package com.highgag.sbook.bookmark.domain;

public enum Importance {
    ZERO(0),
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5);

    private final int score;

    Importance(int score){
        this.score = score;
    }
}
