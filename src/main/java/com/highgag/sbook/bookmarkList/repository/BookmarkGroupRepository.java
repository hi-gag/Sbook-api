package com.highgag.sbook.bookmarkList.repository;

import com.highgag.sbook.bookmark.domain.Bookmark;
import com.highgag.sbook.bookmarkList.domain.BookmarkGroup;
import com.highgag.sbook.bookmarkList.domain.BookmarkList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkGroupRepository extends JpaRepository<BookmarkGroup, Long> {
    void deleteByBookmarkListAndBookmark (BookmarkList bookmarkList, Bookmark bookmark);
}
