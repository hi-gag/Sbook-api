package com.highgag.sbook.bookmarkList.service;

import com.highgag.sbook.bookmark.domain.Bookmark;
import com.highgag.sbook.bookmark.service.BookmarkService;
import com.highgag.sbook.bookmarkList.repository.BookmarkListRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
class BookmarkListServiceTest {
    @Autowired BookmarkService bookmarkService;
    @Autowired BookmarkListService bookmarkListService;

    @Test
    void saveNewBookmarkInExistingBookmarkList_UserNotAuthorized_ForbiddenException() {
        Bookmark bm = new Bookmark();

    }
}