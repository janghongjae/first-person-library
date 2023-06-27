package com.reading.lib.global.aladinapi;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class AladinItems {

    private String isbn;
    private String title;
    private String link;
    private String publisher;
    private String pubDate;
    private String year;
    private String month;
    private String author;
    private String authorTypeAuthor = "없음";
    private String authorTypeTranslator = "없음";
    private String description;
    private String cover;

}
