package com.highgag.sbook.bookmarkList.service;

import com.highgag.sbook.bookmark.domain.Bookmark;
import com.highgag.sbook.bookmark.dto.BookmarkResponse;
import com.highgag.sbook.bookmark.service.BookmarkService;
import com.highgag.sbook.bookmarkList.domain.BookmarkGroup;
import com.highgag.sbook.bookmarkList.domain.BookmarkList;
import com.highgag.sbook.bookmarkList.dto.BookmarkListRequest;
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
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BookmarkListService {

    private final BookmarkListRepository bookmarkListRepository;
    private final BookmarkService bookmarkService;

    public List<BookmarkListResponse> findAllByUser(User user) {
        List<BookmarkListResponse> response = bookmarkListRepository.findAllByOwner(user)
                .stream().map(BookmarkListResponse::new).collect(Collectors.toList());
        return response;
    }

    public BookmarkList findById(Long id, User user){
        Optional<BookmarkList> list =  bookmarkListRepository.findById(id);
        if (list.isPresent()){
            BookmarkList bm =  list.get();
            if (bm.is_shared() == true || bm.getOwner().getEmail().equals(user.getEmail())){
                return bm;
            }
            else {
                throw new ForbiddenException();
            }
        } else {
            throw new NoSuchElementException();
        }
    }
    public void save(BookmarkListRequest request, User user){
        BookmarkList bookmarkList = new BookmarkList(request, user);
        bookmarkListRepository.save(bookmarkList);
    }

    public void save(Long bookmarkId, Long bookmarkListId, User user){
        BookmarkGroup bookmarkGroup = new BookmarkGroup();
        bookmarkGroup.setBookmark(bookmarkService.findOne(user, bookmarkId));
        bookmarkGroup.setBookmarkList(findById(bookmarkListId, user));
        bookmarkListRepository.save(bookmarkGroup);
    }

    public void put(Long bookmarkListId, BookmarkListRequest request, User user){
        BookmarkList toBeUpdated = findById(bookmarkListId, user);
        toBeUpdated.setTitle(request.getTitle());
        toBeUpdated.set_shared(request.isShared());
        bookmarkListRepository.save(toBeUpdated);
    }

    public boolean share(Long bookmarkListId, User user){
        BookmarkList toBeUpdated = findById(bookmarkListId, user);
        toBeUpdated.set_shared(!toBeUpdated.is_shared());
        bookmarkListRepository.save(toBeUpdated);
        return toBeUpdated.is_shared();
    }

    public void delete(Long bookmarkListId, User user){
        BookmarkList bookmarkList = findById(bookmarkListId, user);
        bookmarkListRepository.delete(bookmarkList);
    }
}
