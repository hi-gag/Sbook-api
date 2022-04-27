package com.highgag.sbook.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.highgag.sbook.bookmark.domain.Bookmark;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
//@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String username;

    @NotNull
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @NotNull
    private String password;

    private String uuid;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Bookmark> bookmarkList = new ArrayList<>();

    @Builder
    public void saveEntity(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = Role.ROLE_USER;
    }
}
