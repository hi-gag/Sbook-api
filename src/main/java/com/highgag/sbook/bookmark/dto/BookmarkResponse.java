package com.highgag.sbook.bookmark.dto;

import com.highgag.sbook.bookmark.domain.Bookmark;
import com.highgag.sbook.bookmark.domain.Importance;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@RequiredArgsConstructor
public class BookmarkResponse {
    private Long id;
    private String title;
    private String intro;
    private String description;
    private String url;
    private String image;
    private List<String> keywords;
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
        if (bookmark.getKeywords() != null) {
        this.keywords = Arrays.asList(bookmark.getKeywords().split(","));
        }
    }

    public static BookmarkResponse from(Bookmark bookmark) {
        return new BookmarkResponse(bookmark);
    }
}
