package com.highgag.sbook.bookmark.dto;

import com.highgag.sbook.bookmark.domain.Bookmark;
import com.highgag.sbook.bookmark.domain.Importance;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class BookmarkResponse {
    private Long id;
    private String title;
    private String intro;
    private String description;
    private String url;
    private String image;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String memo;
    private int importance;

    public BookmarkResponse(Bookmark bookmark){
        this.id = bookmark.getId();
        this.title = bookmark.getTitle();
        this.intro = bookmark.getIntro();
        this.description = bookmark.getDescription();
        this.url = bookmark.getUrl();
        this.image = bookmark.getImage();
        this.createdAt = bookmark.getCreatedAt();
        this.updatedAt = bookmark.getUpdatedAt();
        this.memo = bookmark.getMemo();
        this.importance = bookmark.getImportance().ordinal();
    }

    public static BookmarkResponse from(Bookmark bookmark) {
        return new BookmarkResponse(bookmark);
    }
}
