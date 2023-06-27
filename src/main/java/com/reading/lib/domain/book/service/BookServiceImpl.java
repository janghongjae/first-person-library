package com.reading.lib.domain.book.service;

import com.reading.lib.domain.search.dto.RecentKeywordDto;
import com.reading.lib.domain.search.mapper.SearchMapper;
import com.reading.lib.global.aladinapi.AladinAPI;
import com.reading.lib.global.aladinapi.AladinBookDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BookServiceImpl implements BookService{

    private final AladinAPI aladinAPI;
    private final SearchMapper searchMapper;

    public BookServiceImpl(AladinAPI aladinAPI, SearchMapper searchMapper) {
        this.aladinAPI = aladinAPI;
        this.searchMapper = searchMapper;
    }

    @Override
    public AladinBookDTO searchBook(String query, int page) {

        AladinBookDTO aladinBookDTO = aladinAPI.getSearchBooks(query, page);

        aladinAPI.getPubDateHandler(aladinBookDTO.getItem());
        aladinAPI.getAuthorsHandler(aladinBookDTO.getItem());

        String userId = "wkdghdwo12@naver.com"; // 임시

        RecentKeywordDto keyword = searchMapper.findByKeyword(query, userId);

        if (keyword == null) {
            searchMapper.insertKeyword(query, LocalDateTime.now(), userId);
        }   searchMapper.updateKeyword(query, LocalDateTime.now(), userId);

        return aladinBookDTO;
    }
}
