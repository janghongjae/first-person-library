package com.reading.lib.domain.search.controller;

import com.reading.lib.domain.search.dto.RecentKeywordDto;
import com.reading.lib.domain.search.service.SearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Search", description = "검색 관련 api")
@RestController
@RequestMapping("/recentKeyword")
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @Operation(summary = "최근 검색어 조회", description = "최근 검색어를 조회하는 메서드입니다.")
    @GetMapping
    public ResponseEntity<List<RecentKeywordDto>> getRecentKeyword(@AuthenticationPrincipal String email)  {

        List<RecentKeywordDto> response = searchService.getRecentKeyword(email);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "최근 검색어 전체 삭제", description = "최근 검색어를 전체 삭제하는 메서드입니다.")
    @DeleteMapping("/all")
    public ResponseEntity deleteKeywords(@AuthenticationPrincipal String email) {

        searchService.deleteKeywords(email);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "최근 검색어 삭제", description = "최근 검색어를 삭제하는 메서드입니다.")
    @DeleteMapping
    public ResponseEntity deleteKeyword(@AuthenticationPrincipal String email,
                                        @RequestParam(value = "keyword") String keyword) {

        searchService.deleteKeyword(keyword, email);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
