package com.reading.lib.domain.comment.service;

import com.reading.lib.domain.comment.dto.CommentPostDto;
import com.reading.lib.domain.comment.dto.CommentResponseDto;
import com.reading.lib.global.dto.MultiResponseDto;

public interface CommentService {

    MultiResponseDto findAllWithPaging(int pageNumber);

    CommentResponseDto findCommentById(String commentId);

    void createComment(CommentPostDto commentPostDto);

    void updateComment(CommentPostDto commentPostDto);

    void deleteComment(String commentId);
}