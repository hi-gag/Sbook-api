package com.highgag.sbook.bookmark.dto;

import com.highgag.sbook.bookmark.domain.Bookmark;
import com.highgag.sbook.bookmark.domain.Importance;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class BookmarkRequest {
    @Id
    private Long id;

    private String title;

    private String intro;

    private String description;

    @NotNull
    private String url;

    private String image;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private String memo;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Importance importance;

    BookmarkRequest (Bookmark bookmark){

    }
}
