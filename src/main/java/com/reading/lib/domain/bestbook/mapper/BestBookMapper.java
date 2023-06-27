package com.reading.lib.domain.bestbook.mapper;

import com.reading.lib.domain.bestbook.dto.BestBookDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BestBookMapper {

    void deleteBestBooks();
    void insertBestBook(int ranking, String bookId, int commentCount);
    List<BestBookDto> getBestBooks();
}
