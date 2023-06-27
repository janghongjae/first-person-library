package com.reading.lib.domain.comment.dto;

import lombok.Data;

@Data
public class CommentCountDto {

    private String bookId;
    private int commentCount;
}
