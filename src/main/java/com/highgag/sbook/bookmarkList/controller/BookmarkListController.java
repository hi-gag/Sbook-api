package com.highgag.sbook.bookmarkList.controller;

import com.highgag.sbook.bookmark.domain.Bookmark;
import com.highgag.sbook.bookmark.service.BookmarkService;
import com.highgag.sbook.bookmarkList.domain.BookmarkList;
import com.highgag.sbook.bookmarkList.dto.BookmarkGroupResponse;
import com.highgag.sbook.bookmarkList.dto.BookmarkListResponse;
import com.highgag.sbook.bookmarkList.service.BookmarkListService;
import com.highgag.sbook.common.auth.PrincipalDetails;
import com.highgag.sbook.common.dto.GeneralResponse;
import com.highgag.sbook.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class BookmarkListController {
    private final BookmarkListService bookmarkListService;
    private final BookmarkService bookmarkService;

    @GetMapping(value = "bookmarks", produces = "application/json;charset=UTF-8")
    public GeneralResponse getBookmarks (@AuthenticationPrincipal PrincipalDetails principalDetails) {
        User user = principalDetails.getUser();
        GeneralResponse<List<BookmarkListResponse>> response = new GeneralResponse<>();
        List<BookmarkListResponse> allByUser = bookmarkListService.findAllByUser(user);
        response.setData("200", "정상적으로 조회되었습니다", allByUser);
        return response;
    }

    @GetMapping(value = "bookmarks/{bookmarkListId}", produces = "application/json;charset=UTF-8")
    public GeneralResponse getBookmarkList (@PathVariable("bookmarkListId") Long bookmarkListId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        User user = principalDetails.getUser();
        GeneralResponse<BookmarkGroupResponse> response = new GeneralResponse<>();
        BookmarkGroupResponse bookmarkGroupResponse = new BookmarkGroupResponse();
        bookmarkGroupResponse.setBookmarks(bookmarkListService.findById(bookmarkListId, user), bookmarkService.findAllByBookmarkList(bookmarkListId));
        response.setData("200", "정상적으로 조회되었습니다", bookmarkGroupResponse);
        return response;
    }
}
