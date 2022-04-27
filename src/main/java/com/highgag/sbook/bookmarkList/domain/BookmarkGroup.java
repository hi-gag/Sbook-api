package com.highgag.sbook.bookmarkList.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.highgag.sbook.bookmark.domain.Bookmark;
import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
public class BookmarkGroup {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "bookmark_id")
    private Bookmark bookmark;

    @JsonIgnore
    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "bookmarkList_id")
    private BookmarkList bookmarkList;
}
