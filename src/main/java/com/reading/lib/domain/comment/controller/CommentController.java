package com.reading.lib.domain.comment.controller;

import com.reading.lib.domain.comment.dto.CommentPostDto;
import com.reading.lib.domain.comment.dto.CommentResponseDto;
import com.reading.lib.domain.comment.service.CommentService;
import com.reading.lib.global.dto.MultiResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Comment", description = "코멘트 관련 api")
@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @Operation(summary = "전체 코멘트 목록 조회", description = "전체 코멘트 목록을 조회하는 메서드입니다.")
    @GetMapping
    public ResponseEntity<MultiResponseDto> getAllCommentWithPaging(@RequestParam("pageNumber") int pageNumber) {

       MultiResponseDto response = commentService.findAllWithPaging(pageNumber);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "특정 코멘트 정보 조회", description = "특정 코멘트의 정보를 조회하는 메서드입니다.")
    @GetMapping("/id")
    public ResponseEntity<CommentResponseDto> getCommentById(@RequestParam(value = "commentId") String commentId) {

        CommentResponseDto response = commentService.findCommentById(commentId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "코멘트 등록", description = "코멘트를 등록하는 메서드입니다.")
    @PostMapping
    public ResponseEntity postComment(@RequestBody CommentPostDto requestBody) {

        commentService.createComment(requestBody);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "코멘트 수정", description = "코멘트를 수정하는 메서드입니다.")
    @PatchMapping
    public ResponseEntity patchComment(@RequestBody CommentPostDto requestBody) {

        commentService.updateComment(requestBody);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "코멘트 삭제", description = "코멘트를 삭제하는 메서드입니다.")
    @DeleteMapping
    public ResponseEntity deleteComment(@RequestParam(value = "commentId") String commentId) {

        commentService.deleteComment(commentId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}