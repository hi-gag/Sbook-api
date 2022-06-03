package com.highgag.sbook.bookmarkList.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.highgag.sbook.bookmarkList.domain.BookmarkList;
import com.highgag.sbook.user.domain.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class BookmarkListResponse {
    private Long id;
    private String owner;
    private boolean isShared;
    private String title;

    public BookmarkListResponse(BookmarkList bookmarkList){
        this.id = bookmarkList.getId();
        this.owner = bookmarkList.getOwner().getUsername();
        this.isShared = bookmarkList.is_shared();
        this.title = bookmarkList.getTitle();
    }
}
