package com.reading.lib.domain.comment.service;

import com.reading.lib.domain.book.dto.BookDto;
import com.reading.lib.domain.book.mapper.BookMapper;
import com.reading.lib.domain.comment.dto.CommentPatchDto;
import com.reading.lib.domain.comment.dto.CommentPostDto;
import com.reading.lib.domain.comment.dto.CommentResponseDto;
import com.reading.lib.domain.comment.mapper.CommentMapper;
import com.reading.lib.global.dto.MultiResponseDto;
import com.reading.lib.global.exception.BusinessLogicException;
import com.reading.lib.global.exception.ExceptionCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private static int pageSize = 16;

    private final CommentMapper commentMapper;
    private final BookMapper bookMapper;

    public CommentServiceImpl(CommentMapper commentMapper, BookMapper bookMapper) {
        this.commentMapper = commentMapper;
        this.bookMapper = bookMapper;
    }

    @Override
    public MultiResponseDto findAllWithPaging(int pageNumber) {
        int offset = (pageNumber - 1) * pageSize;
        int limit = pageSize;

        List<CommentResponseDto> comments = commentMapper.findAllWithPaging(offset, limit);
        int totalItems = commentMapper.countAll();
        int totalPages = (int) Math.ceil((double) totalItems / pageSize);

        getCoverHandler(comments);

        MultiResponseDto responseDto = new MultiResponseDto();
        responseDto.setCommentResponseDto(comments);
        responseDto.setCurrentPage(pageNumber);
        responseDto.setPageSize(pageSize);
        responseDto.setTotalItems(totalItems);
        responseDto.setTotalPages(totalPages);

        return responseDto;
    }

    @Override
    public CommentResponseDto findCommentById(String commentId) {
        return commentMapper.findById(commentId);
    }

    // 파라미터에 user_id 추가 필요(컨트롤러단에서 로그인정보를 가져와서 id 추출)
    @Override
    public void createComment(CommentPostDto commentPostDto /*,String userId*/) {

        String commentId = commentPostDto.getCommentId();
        String content = commentPostDto.getContent();
        String color = commentPostDto.getColor();
        boolean blur = commentPostDto.getBlur();
        BookDto bookDto = commentPostDto.getBookDto();

        String userId = "wkdghdwo12@naver.com"; //임시
        String bookId = bookDto.getIsbn();

        /*if (commentMapper.hasCommented(userId, bookId)) {
            throw new BusinessLogicException(ExceptionCode.COMMENT_ALREADY_EXISTS);
        }*/

        //book 저장(데이터 존재 유무에 따른 저장)
        BookDto findBook = bookMapper.findById(bookId);

        if (findBook == null) {
            bookMapper.saveBook(bookDto);
        }

        //comment 저장
        commentMapper.insertComment(commentId, content, color, blur, userId, bookId);
    }

    @Override
    public void updateComment(CommentPostDto commentPostDto /*,String userId*/) {

        String commentId = commentPostDto.getCommentId();
        String content = commentPostDto.getContent();
        String color = commentPostDto.getColor();
        boolean blur = commentPostDto.getBlur();
        BookDto bookDto = commentPostDto.getBookDto();

        String userId = "wkdghdwo12@naver.com"; //임시
        String bookId = bookDto.getIsbn();

        String findUserId = findCommentById(commentId).getUserId();

        CommentPatchDto commentPatchDto = new CommentPatchDto();
        commentPatchDto.setCommentId(commentId);
        commentPatchDto.setContent(content);
        commentPatchDto.setBookId(bookId);
        commentPatchDto.setColor(color);
        commentPatchDto.setBlur(blur);

        if (!userId.equals(findUserId)) {
            throw new BusinessLogicException(ExceptionCode.CANNOT_UPDATE_COMMENT);
        }

        //book 저장(데이터 존재 유무에 따른 저장)
        BookDto findBook = bookMapper.findById(bookId);

        if (findBook == null) {
            bookMapper.saveBook(bookDto);
        }

        //comment 수정
        commentMapper.updateComment(commentPatchDto);
    }

    @Override
    public void deleteComment(String commentId) {

        commentMapper.deleteComment(commentId);
    }

    private void getCoverHandler(List<CommentResponseDto> commentList) {

        for (CommentResponseDto comment : commentList) {

            String cover = comment.getBookDto().getCover().replace("cover", "coversum");
            comment.getBookDto().setCover(cover);
        }
    }
}