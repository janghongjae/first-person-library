package com.reading.lib.domain.book.mapper;

import com.reading.lib.domain.book.dto.BookDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookMapper {

    BookDto findById(String Id);
    void saveBook(BookDto bookDto);
}
