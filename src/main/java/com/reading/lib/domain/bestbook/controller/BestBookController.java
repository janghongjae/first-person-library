package com.reading.lib.domain.bestbook.controller;

import com.reading.lib.domain.bestbook.dto.BestBookDto;
import com.reading.lib.domain.bestbook.service.BestBookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "BestBook", description = "베스트 도서 관련 api")
@RestController
@RequestMapping("/bestBooks")
public class BestBookController {

    private final BestBookService bestBookService;

    public BestBookController(BestBookService bestBookService) {
        this.bestBookService = bestBookService;
    }

    @Operation(summary = "베스트 도서 조회", description = "베스트 도서를 조회하는 메서드입니다.")
    @GetMapping
    public ResponseEntity<List<BestBookDto>> getBestBooks() {

        List<BestBookDto> response = bestBookService.getBestBooks();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}