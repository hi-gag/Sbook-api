package com.highgag.sbook.bookmarkList.domain;

import com.highgag.sbook.bookmark.domain.Bookmark;
import com.highgag.sbook.user.domain.User;
import lombok.Getter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

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
