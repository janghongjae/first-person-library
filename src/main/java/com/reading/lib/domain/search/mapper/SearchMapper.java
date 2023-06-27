package com.reading.lib.domain.search.mapper;

import com.reading.lib.domain.search.dto.RecentKeywordDto;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface SearchMapper {

    void insertKeyword(String keyword, LocalDateTime createdAt, String userId);
    void updateKeyword(String keyword, LocalDateTime createdAt, String userId);
    RecentKeywordDto findByKeyword(String keyword, String userId);
    List<RecentKeywordDto> getRecentKeyword(String userId);
    void deleteKeywords(String userId);
    void deleteKeyword(String keyword, String userId);
}
