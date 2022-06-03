package com.highgag.sbook.bookmarkList.dto;

import com.highgag.sbook.bookmarkList.domain.BookmarkList;
import com.highgag.sbook.user.domain.User;

public class BookmarkGroupResponse<T> {
    public Long id;
    public String owner;
    public boolean isShared;
    private T bookmarks;

    public T getBookmarks() {
        return bookmarks;
    }

    public BookmarkGroupResponse setBookmarks(BookmarkList bookmarkList, T bookmarks) {
        this.id = bookmarkList.getId();
        this.owner = bookmarkList.getOwner().getUsername();
        this.isShared = bookmarkList.is_shared();
        this.bookmarks = bookmarks;
        return this;
    }
}
