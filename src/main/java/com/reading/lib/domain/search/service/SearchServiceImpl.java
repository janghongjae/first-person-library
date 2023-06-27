package com.reading.lib.domain.search.service;

import com.reading.lib.domain.search.dto.RecentKeywordDto;
import com.reading.lib.domain.search.mapper.SearchMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService{

    private final SearchMapper searchMapper;

    public SearchServiceImpl(SearchMapper searchMapper) {
        this.searchMapper = searchMapper;
    }

    @Override
    public List<RecentKeywordDto> getRecentKeyword(String userId){

        return searchMapper.getRecentKeyword(userId);
    }

    @Override
    public void deleteKeywords(String userId) {
        searchMapper.deleteKeywords(userId);
    }

    @Override
    public void deleteKeyword(String keyword, String userId) {
        searchMapper.deleteKeyword(keyword, userId);
    }
}
