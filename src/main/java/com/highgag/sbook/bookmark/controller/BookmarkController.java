package com.highgag.sbook.bookmark.controller;

import com.highgag.sbook.bookmark.domain.Bookmark;
import com.highgag.sbook.bookmark.service.BookmarkService;
import com.highgag.sbook.common.dto.GeneralResponse;
import com.highgag.sbook.error.ForbiddenException;
import com.highgag.sbook.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @GetMapping(value = "bookmark/{bookmarkId}", produces = "application/json;charset=UTF-8")
    public GeneralResponse getBookmark (@PathVariable("bookmarkId") Long bookmarkId, @AuthenticationPrincipal User user) {
        GeneralResponse<Object> response = new GeneralResponse<>();
        Bookmark bookmark = bookmarkService.findOne(user, bookmarkId);
        response.setData("200", "정상적으로 조회되었습니다", bookmark);
        return response;
    }
}
