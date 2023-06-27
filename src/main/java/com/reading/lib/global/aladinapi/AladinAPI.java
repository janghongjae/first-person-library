package com.reading.lib.global.aladinapi;

import java.util.List;

public interface AladinAPI {

    AladinBookDTO getSearchBooks(String keyword, int page);

    void getPubDateHandler(List<AladinItems> itemsList);

    void getAuthorsHandler(List<AladinItems> itemsList);
}
