package com.highgag.sbook.bookmark.repository;

import com.highgag.sbook.bookmark.domain.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    @Override
    Optional<Bookmark> findById(Long id);

    Bookmark save(Bookmark bookmark);
}
