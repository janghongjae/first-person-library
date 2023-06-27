package com.reading.lib.domain.bestbook.service;

import com.reading.lib.domain.bestbook.dto.BestBookDto;

import java.util.List;

public interface BestBookService {

    void updateMonthlyBestBooks();
    List<BestBookDto> getBestBooks();
}
