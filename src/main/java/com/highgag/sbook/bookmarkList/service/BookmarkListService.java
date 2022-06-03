package com.highgag.sbook.bookmarkList.service;

import com.highgag.sbook.bookmark.service.BookmarkService;
import com.highgag.sbook.bookmarkList.domain.BookmarkList;
import com.highgag.sbook.bookmarkList.dto.BookmarkListResponse;
import com.highgag.sbook.bookmarkList.repository.BookmarkListRepository;
import com.highgag.sbook.error.ForbiddenException;
import com.highgag.sbook.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BookmarkListService {

    private final BookmarkListRepository bookmarkListRepository;
    private final BookmarkService bookmarkService;

    public List<BookmarkListResponse> findAllByUser(User user) {
        List<BookmarkList> allByUser = bookmarkListRepository.findAllByOwner(user);
        List<BookmarkListResponse> response = new ArrayList<BookmarkListResponse>();
        for (BookmarkList bmList : allByUser){
            response.add(new BookmarkListResponse(bmList));
        }
        return response;
    }

    public BookmarkList findById(Long id, User user){
        Optional<BookmarkList> list =  bookmarkListRepository.findById(id);
        if (list.isPresent()){
            BookmarkList bm =  list.get();
            if (bm.is_shared() == true){
                return bm;
            }
            else {
                throw new ForbiddenException();
            }
        } else {
            throw new NoSuchElementException();
        }
    }
}
