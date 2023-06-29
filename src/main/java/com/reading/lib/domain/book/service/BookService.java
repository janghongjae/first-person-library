package com.reading.lib.domain.book.service;

import com.reading.lib.global.aladinapi.AladinBookDTO;

public interface BookService {

    AladinBookDTO searchBook(String query, int page, String userId);
}
