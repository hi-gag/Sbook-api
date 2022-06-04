package com.highgag.sbook.bookmarkList.dto;

import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Data
public class BookmarkListRequest {

    @NotNull
    private String title;

    @Getter @NotNull
    private boolean shared;
}
