package com.highgag.sbook.domain;

import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
public class Bookmark {
    @Id
    @Column(name = "bookmark_id")
    private Long id;

    private String title;
    private String intro;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
