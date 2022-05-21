package com.highgag.sbook.bookmark.controller;

import com.highgag.sbook.bookmark.domain.Bookmark;
import com.highgag.sbook.bookmark.dto.BookmarkRequest;
import com.highgag.sbook.bookmark.service.BookmarkService;
import com.highgag.sbook.common.dto.GeneralResponse;
import com.highgag.sbook.error.ForbiddenException;
import com.highgag.sbook.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

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

    @PostMapping(value = "bookmark", produces = "application/json;charset=UTF-8")
    public GeneralResponse postBookmark (@AuthenticationPrincipal User user, @Valid @RequestBody BookmarkRequest request){
        GeneralResponse<Object> response = new GeneralResponse<>();
        Bookmark bookmark = request;
        bookmarkService.save(bookmark, user);
        response.setData("201", "정상적으로 저장되었습니다");
        return  response;
    }



}
