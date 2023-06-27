package com.reading.lib.domain.comment.dto;

import lombok.Data;

@Data
public class CommentPatchDto {

    private String commentId;
    private String content;
    private String color;
    private boolean blur;
    private String bookId;
}
