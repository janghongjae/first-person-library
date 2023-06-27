package com.reading.lib.domain.comment.dto;

import com.reading.lib.domain.book.dto.BookDto;
import lombok.Data;

@Data
public class CommentResponseDto {

    private String commentId;
    private String content;
    private String color;
    private Boolean blur;
    private String userId;
    private BookDto bookDto;
}
