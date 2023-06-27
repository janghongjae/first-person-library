package com.reading.lib.global.aladinapi;

import lombok.Data;

import java.util.List;

@Data
public class AladinBookDTO {

    private String query;
    private int totalResults;
    private int startIndex;
    private int itemsPerPage;
    private List<AladinItems> item;
}