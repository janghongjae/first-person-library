package com.reading.lib.global.dto;


import com.reading.lib.domain.comment.dto.CommentResponseDto;
import lombok.Data;

import java.util.List;

@Data
public class MultiResponseDto {

    private int currentPage;
    private int pageSize;
    private int totalItems;
    private int totalPages;
    private List<CommentResponseDto> commentResponseDto;
}
