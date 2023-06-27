package com.reading.lib.domain.book.dto;

import lombok.Data;

@Data
public class BookDto {

    private String isbn;
    private String title;
    private String link;
    private String author;
    private String translator;
    private String publisher;
    private String year;
    private String month;
    private String description;
    private String cover;
}