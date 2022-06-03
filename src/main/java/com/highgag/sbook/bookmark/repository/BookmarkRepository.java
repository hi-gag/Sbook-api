package com.highgag.sbook.bookmark.repository;

import com.highgag.sbook.bookmark.domain.Bookmark;
import com.highgag.sbook.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    @Override
    Optional<Bookmark> findById(Long id);

    Bookmark save(Bookmark bookmark);
}
