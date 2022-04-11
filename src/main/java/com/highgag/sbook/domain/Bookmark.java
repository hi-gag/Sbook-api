package com.highgag.sbook.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
public class Bookmark {
    @Id
    private Long id;

    private String title;

    private String intro;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String url;

    private String image;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    private String memo;

    @JsonIgnore
    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "bookmarkList_id")
    private BookmarkList bookmarkList;

    @Enumerated(EnumType.STRING)
    private Importance importance; //ONE, TWO, THREE, FOUR, FIVE

}
