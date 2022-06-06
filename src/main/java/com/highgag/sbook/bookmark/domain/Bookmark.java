package com.highgag.sbook.bookmark.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.highgag.sbook.bookmark.dto.BookmarkRequest;
import com.highgag.sbook.bookmarkList.domain.BookmarkGroup;
import com.highgag.sbook.bookmarkList.domain.BookmarkList;
import com.highgag.sbook.user.domain.User;
import lombok.Getter;
import javax.persistence.*;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class Bookmark {
    @Id @GeneratedValue
    private Long id;

    private String title;

    private String intro;

    private String description;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
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

    @OneToMany(mappedBy = "bookmark", cascade = CascadeType.REMOVE)
    private List<BookmarkGroup> bookmarkGroupList = new ArrayList<>();

    private String keywords;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Importance importance; //ONE, TWO, THREE, FOUR, FIVE

    public Bookmark(BookmarkRequest request, User user){
        this.title = request.getTitle();
        this.intro = request.getIntro();
        this.description = request.getDescription();
        this.user = user;
        this.url = request.getUrl();
        this.image = request.getImage();
        this.memo = request.getMemo();
        this.importance = request.getImportance();
        if (request.getKeywords() != null) {
            this.keywords = String.join(",", request.getKeywords());
        }
    }

    public Bookmark() {
    }
}
