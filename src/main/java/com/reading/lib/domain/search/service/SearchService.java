package com.reading.lib.domain.search.service;

import com.reading.lib.domain.search.dto.RecentKeywordDto;

import java.util.List;

public interface SearchService {

    List<RecentKeywordDto> getRecentKeyword(String userId);
    void deleteKeywords(String userId);
    void deleteKeyword(String keyword, String userId);
}
