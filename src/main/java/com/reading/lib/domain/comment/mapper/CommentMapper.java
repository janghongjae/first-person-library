package com.reading.lib.domain.comment.mapper;

import com.reading.lib.domain.comment.dto.CommentCountDto;
import com.reading.lib.domain.comment.dto.CommentPatchDto;
import com.reading.lib.domain.comment.dto.CommentResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {

    CommentResponseDto findById(String commentId);

    List<CommentResponseDto> findAllWithPaging(@Param("offset") int offset, @Param("limit") int limit);
    void insertComment(String commentId, String content, String color, boolean blur, String userId, String bookId);
    void updateComment(CommentPatchDto commentPatchDto);
    void deleteComment(String commentId);

    boolean hasCommented(String userId, String bookId);
    int countAll();
    List<CommentCountDto> calculateTotalCommentCount();
}
