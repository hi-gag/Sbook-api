package com.highgag.sbook.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.highgag.sbook.bookmark.domain.Bookmark;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
//@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String password;

    private String uuid;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Bookmark> bookmarkList = new ArrayList<>();

    @Builder
    public void saveUser(String email, String password){
        this.email = email;
        this.password = password;
        this.role = Role.USER;
    }
}
