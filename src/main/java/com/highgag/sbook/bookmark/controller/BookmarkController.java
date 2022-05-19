package com.highgag.sbook.bookmark.controller;

import com.highgag.sbook.bookmark.domain.Bookmark;
import com.highgag.sbook.bookmark.service.BookmarkService;
import com.highgag.sbook.common.dto.GeneralResponse;
import com.highgag.sbook.error.ForbiddenException;
import com.highgag.sbook.user.domain.User;
import com.highgag.sbook.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;
    private final UserService userService;

    //특정 북마크 조회
    @GetMapping(value = "bookmark/{bookmarkId}", produces = "application/json;charset=UTF-8")
    public GeneralResponse getBookmark (@PathVariable("bookmarkId") Long bookmarkId, @AuthenticationPrincipal User user) {
        GeneralResponse<Object> response = new GeneralResponse<>();

        Bookmark bookmark = bookmarkService.findOne(bookmarkId);

        //해당 북마크가 존재하지 않는 경우
        if (bookmark == null) response.setData("404", "존재하지 않는 데이터입니다");
        //북마크 존재하는 경우
        else{
            //해당 북마크의 북마크 리스트가 공유되었다면
            if (bookmark.getBookmarkList().is_shared()) response.setData("200", "정상적으로 조회되었습니다", bookmark);
            //공유되지 않았다면
            else{
                //로그인이 안되어 있다면
                if (user == null) response.setData("403", "권한이 없습니다");
                //로그인이 되어 있다면
                else{
                    //해당 북마크가 유저 소유라면 조회
                    try{
                        userService.isAuthorized(user, bookmark);
                        response.setData("200", "정상적으로 조회되었습니다", bookmark);
                    }
                    catch (ForbiddenException e){
                        //throw e;
                        response.setData("403", "권한이 없습니다");
                    }
                }
            }
        }
        return response;
    }
}
