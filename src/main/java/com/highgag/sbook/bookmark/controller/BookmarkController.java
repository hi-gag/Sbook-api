package com.highgag.sbook.bookmark.controller;

import com.highgag.sbook.bookmark.domain.Bookmark;

import com.highgag.sbook.bookmark.dto.BookmarkRequest;
import com.highgag.sbook.bookmark.dto.BookmarkResponse;
import com.highgag.sbook.bookmark.service.BookmarkService;
import com.highgag.sbook.common.auth.PrincipalDetails;
import com.highgag.sbook.common.dto.GeneralResponse;
import com.highgag.sbook.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;
    private final HttpStatus CREATED = HttpStatus.CREATED;

    @GetMapping(value = "bookmark/{bookmarkId}", produces = "application/json;charset=UTF-8")
    public GeneralResponse<BookmarkResponse> getBookmark (@PathVariable("bookmarkId") Long bookmarkId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        User user = principalDetails.getUser();
        GeneralResponse<BookmarkResponse> response = new GeneralResponse<>();
        BookmarkResponse bookmark = BookmarkResponse.from(bookmarkService.findOne(user, bookmarkId));
        response.setData("200", "정상적으로 조회되었습니다", bookmark);
        return response;
    }

    @PostMapping(value = "bookmark", produces = "application/json;charset=UTF-8")
    public ResponseEntity postBookmark (@Valid @RequestBody BookmarkRequest request, @AuthenticationPrincipal PrincipalDetails principalDetails){
        User user = principalDetails.getUser();
        GeneralResponse<Object> response = new GeneralResponse<>();
        Bookmark bookmark = new Bookmark(request, user);
        bookmarkService.save(bookmark, user);
        response.setData("201", "정상적으로 저장되었습니다");
        return new ResponseEntity(response, CREATED);
    }

    @PutMapping(value = "bookmark/{bookmarkId}", produces = "application/json;charset=UTF-8")
    public GeneralResponse putBookmark (@PathVariable("bookmarkId") Long bookmarkId, @AuthenticationPrincipal PrincipalDetails principalDetails, @Valid @RequestBody BookmarkRequest request){
        GeneralResponse<Object> response = new GeneralResponse<>();
        bookmarkService.put(bookmarkId, request);
        response.setData("200", "정상적으로 수정되었습니다.");
        return response;
    }

    @DeleteMapping(value = "bookmark/{bookmarkId}", produces = "application/json;charset=UTF-8")
    public GeneralResponse deleteBookmark (@PathVariable("bookmarkId") Long bookmarkId, @AuthenticationPrincipal PrincipalDetails principalDetails){
        User user = principalDetails.getUser();
        GeneralResponse<Object> response = new GeneralResponse<>();
        bookmarkService.deleteOne(bookmarkId, user);
        response.setData("200", "정상적으로 삭제되었습니다.");
        return response;
    }
}
