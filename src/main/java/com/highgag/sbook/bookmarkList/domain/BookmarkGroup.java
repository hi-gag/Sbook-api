package com.highgag.sbook.bookmarkList.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.highgag.sbook.bookmark.domain.Bookmark;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class BookmarkGroup {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "bookmark_id")
    private Bookmark bookmark;

    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "bookmarkList_id")
    private BookmarkList bookmarkList;
}
