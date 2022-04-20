package com.highgag.sbook.bookmarkList.domain;

import com.highgag.sbook.user.domain.User;
import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
public class BookmarkList {
    @Id
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User owner;

    private boolean is_shared;

    private String title;

}
