package com.reading.lib.domain.comment.dto;

import com.reading.lib.domain.book.dto.BookDto;
import lombok.Data;

@Data
public class CommentPostDto {

    private String commentId;
    private String content;
    private String color;
    private Boolean blur;
    private BookDto bookDto;
}