package com.highgag.sbook.bookmark.dto;

import com.highgag.sbook.bookmark.domain.Bookmark;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;

@Data
@RequiredArgsConstructor
@Entity
public class BookmarkRequest extends Bookmark {
}
