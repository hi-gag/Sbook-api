package com.highgag.sbook.bookmark.service;

import com.highgag.sbook.bookmark.domain.Bookmark;
import com.highgag.sbook.bookmark.dto.BookmarkRequest;
import com.highgag.sbook.bookmark.dto.BookmarkResponse;
import com.highgag.sbook.bookmark.repository.BookmarkRepository;
import com.highgag.sbook.bookmarkList.repository.BookmarkListRepository;
import com.highgag.sbook.user.domain.User;
import com.highgag.sbook.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;
    private final BookmarkListRepository bookmarkListRepository;
    private final UserService userService;

    public void save(Bookmark bookmark, User user){
        bookmark.setUser(user);
        bookmarkRepository.save(bookmark);
    }

    public Bookmark findOne(User user, Long id){
        Optional<Bookmark> bookmark = bookmarkRepository.findById(id);
        userService.isAuthorized(user, bookmark.get());
        return bookmark.get();
    }

    public List<BookmarkResponse> findAllByBookmarkList (Long id) {
        List<BookmarkResponse> bookmarks = bookmarkListRepository.findAllByBookmarkList(id).stream().map(BookmarkResponse::new).collect(Collectors.toList());
        return bookmarks;
    }

    public void flush(){
        bookmarkRepository.flush();
    }

    public void put(Long id, BookmarkRequest bookmark){
        Bookmark toBeUpdated = bookmarkRepository.findById(id).get();

        toBeUpdated.setTitle(bookmark.getTitle());
        toBeUpdated.setDescription(bookmark.getDescription());
        toBeUpdated.setUrl(bookmark.getUrl());
        toBeUpdated.setImage(bookmark.getImage());
        toBeUpdated.setMemo(bookmark.getMemo());
        toBeUpdated.setImportance(bookmark.getImportance());
        bookmarkRepository.save(toBeUpdated);
    }

    public void deleteOne(Bookmark bookmark){
        List<Bookmark> bookmarkList = new ArrayList<>();
        bookmarkList.add(bookmark);
        bookmarkRepository.deleteAllInBatch(bookmarkList);
    }
}
