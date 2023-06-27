package com.reading.lib.domain.bestbook.dto;

import lombok.Data;

@Data
public class BestBookDto {

    private int ranking;
    private String bookId;
    private String title;
    private String author;
    private String translator;
    private String description;
    private String cover;
}
