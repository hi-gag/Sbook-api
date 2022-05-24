package com.highgag.sbook.bookmark.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.highgag.sbook.bookmarkList.domain.BookmarkList;
import com.highgag.sbook.user.domain.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class Bookmark {
    @Id
    private Long id;

    private String title;

    private String description;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @NotNull
    private String url;

    private String image;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    private String memo;

    @JsonIgnore
    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "bookmarkList_id")
    private BookmarkList bookmarkList;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Importance importance; //ONE, TWO, THREE, FOUR, FIVE
}
