package com.highgag.sbook.bookmarkList.dto;

import lombok.Data;
import lombok.Getter;

@Data
public class BookmarkListRequest {
    private Long id;

    private String title;

    @Getter
    private boolean shared;
}
