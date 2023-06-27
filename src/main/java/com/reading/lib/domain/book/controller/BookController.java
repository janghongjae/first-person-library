package com.reading.lib.domain.book.controller;

import com.reading.lib.domain.book.service.BookService;
import com.reading.lib.global.aladinapi.AladinAPI;
import com.reading.lib.global.aladinapi.AladinBookDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Book", description = "도서 관련 api")
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @Operation(summary = "도서 검색", description = "도서를 검색하는 메서드입니다.")
    @GetMapping
    public ResponseEntity<AladinBookDTO> getBook(@RequestParam(value = "query") String query,
                                                 @RequestParam(value = "page") int page) {

        AladinBookDTO aladinBookDTO = bookService.searchBook(query, page);

        return new ResponseEntity<>(aladinBookDTO, HttpStatus.OK);
    }
}