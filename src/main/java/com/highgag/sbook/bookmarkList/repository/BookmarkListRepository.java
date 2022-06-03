package com.highgag.sbook.bookmarkList.repository;

import com.highgag.sbook.bookmark.domain.Bookmark;
import com.highgag.sbook.bookmarkList.domain.BookmarkList;
import com.highgag.sbook.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookmarkListRepository extends JpaRepository<BookmarkList, Long> {
    @Override
    Optional<BookmarkList> findById(Long aLong);

    List<BookmarkList> findAllByOwner(User user);

    BookmarkList save(BookmarkList bookmarkList);

    @Query("SELECT bmg.bookmark FROM BookmarkGroup bmg JOIN bmg.bookmarkList bml WHERE bml.id  = :id ")
    List<Bookmark> findAllByBookmarkList (@Param("id") Long id);
}
