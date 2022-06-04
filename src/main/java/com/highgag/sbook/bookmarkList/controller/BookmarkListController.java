package com.highgag.sbook.bookmarkList.controller;

import com.highgag.sbook.bookmark.domain.Bookmark;
import com.highgag.sbook.bookmark.dto.BookmarkRequest;
import com.highgag.sbook.bookmark.service.BookmarkService;
import com.highgag.sbook.bookmarkList.domain.BookmarkList;
import com.highgag.sbook.bookmarkList.dto.BookmarkGroupResponse;
import com.highgag.sbook.bookmarkList.dto.BookmarkListRequest;
import com.highgag.sbook.bookmarkList.dto.BookmarkListResponse;
import com.highgag.sbook.bookmarkList.service.BookmarkListService;
import com.highgag.sbook.common.auth.PrincipalDetails;
import com.highgag.sbook.common.dto.GeneralResponse;
import com.highgag.sbook.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class BookmarkListController {
    private final BookmarkListService bookmarkListService;
    private final BookmarkService bookmarkService;
    private final HttpStatus CREATED = HttpStatus.CREATED;

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

    @PostMapping(value = "bookmarks/{bookmarkListId}", produces = "application/json;charset=UTF-8")
    public ResponseEntity postNewBookmark (@PathVariable("bookmarkListId") Long bookmarkListId, @AuthenticationPrincipal PrincipalDetails principalDetails, @Valid @RequestBody BookmarkRequest request){
        User user = principalDetails.getUser();
        GeneralResponse<Object> response = new GeneralResponse<>();
        Bookmark bookmark = new Bookmark(request, user);
        bookmarkService.save(bookmark, user);
        bookmarkListService.save(bookmark.getId(), bookmarkListId, user);
        response.setData("201", "정상적으로 저장되었습니다");
        return new ResponseEntity(response, CREATED);
    }

    @PostMapping(value = "bookmarks/{bookmarkListId}/bookmark/{bookmarkId}", produces = "application/json;charset=UTF-8")
    public ResponseEntity postBookmark (@PathVariable("bookmarkListId") Long bookmarkListId, @PathVariable("bookmarkId") Long bookmarkId, @AuthenticationPrincipal PrincipalDetails principalDetails){
        User user = principalDetails.getUser();
        bookmarkListService.save(bookmarkId, bookmarkListId, user);
        GeneralResponse<Object> response = new GeneralResponse<>();
        response.setData("201", "정상적으로 저장되었습니다");
        return new ResponseEntity(response, CREATED);
    }

    @PostMapping(value = "bookmarks", produces = "application/json;charset=UTF-8")
    public ResponseEntity postBookmarkList (@AuthenticationPrincipal PrincipalDetails principalDetails, @Valid @RequestBody BookmarkListRequest request){
        User user = principalDetails.getUser();
        GeneralResponse<Object> response = new GeneralResponse<>();
        bookmarkListService.save(request, user);
        response.setData("201", "정상적으로 저장되었습니다");
        return new ResponseEntity(response, CREATED);
    }

    @PutMapping(value = "bookmarks/{bookmarkListId}", produces = "application/json;charset=UTF-8")
    public GeneralResponse putBookmarkList (@PathVariable("bookmarkListId") Long bookmarkListId, @AuthenticationPrincipal PrincipalDetails principalDetails, @Valid @RequestBody BookmarkListRequest request) {
        User user = principalDetails.getUser();
        GeneralResponse<Object> response = new GeneralResponse<>();
        bookmarkListService.put(bookmarkListId, request, user);
        response.setData("200", "정상적으로 수정되었습니다.");
        return response;
    }

    @PutMapping(value = "bookmarks/share/{bookmarkListId}", produces = "application/json;charset=UTF-8")
    public GeneralResponse shareBookmarkList(@PathVariable("bookmarkListId") Long bookmarkListId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        User user = principalDetails.getUser();
        GeneralResponse<Object> response = new GeneralResponse<>();
        Map<String, Boolean> is_shared = new HashMap<>();
        is_shared.put("is_shared", bookmarkListService.share(bookmarkListId, user));
        response.setData("200", "정상적으로 설정되었습니다.", is_shared);
        return response;
    }

    @DeleteMapping(value = "bookmarks/{bookmarkListId}", produces = "application/json;charset=UTF-8")
    public GeneralResponse deleteBookmarkList (@PathVariable("bookmarkListId") Long bookmarkListId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        User user = principalDetails.getUser();
        GeneralResponse<Object> response = new GeneralResponse<>();
        bookmarkListService.delete(bookmarkListId, user);
        response.setData("200", "정상적으로 삭제되었습니다.");
        return response;
    }
}
