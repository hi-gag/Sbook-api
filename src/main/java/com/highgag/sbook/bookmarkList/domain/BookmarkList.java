package com.highgag.sbook.bookmarkList.domain;

import com.highgag.sbook.bookmark.domain.Bookmark;
import com.highgag.sbook.bookmarkList.dto.BookmarkListRequest;
import com.highgag.sbook.user.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class BookmarkList {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User owner;

    private boolean is_shared;

    private String title;

    public BookmarkList(BookmarkListRequest request, User user){
        this.owner = user;
        this.is_shared = request.isShared();
        this.title = request.getTitle();
    }

    public BookmarkList() {
    }
}
