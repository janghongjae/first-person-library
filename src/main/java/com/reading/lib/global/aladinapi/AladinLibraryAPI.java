package com.reading.lib.global.aladinapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AladinLibraryAPI implements AladinAPI {

    @Value("${aladin.ttbKey}")
    private String ttbKey;

    private final String apiUrl = "http://www.aladin.co.kr";
    private final String path = "/ttb/api/ItemSearch.aspx";


    public AladinBookDTO getSearchBooks(String keyword, int page) {
        RestTemplate restTemplate = new RestTemplate();

        URI uriBuilder = UriComponentsBuilder.fromUriString(apiUrl)
                .path(path)
                .queryParam("ttbkey", ttbKey)
                .queryParam("Query", keyword)
                .queryParam("MaxResults", 10)
                .queryParam("start", page)
                .queryParam("output", "js")
                .queryParam("cover", "Big")
                .queryParam("Version", "20131101")
                .encode().build().toUri();

        ResponseEntity<AladinBookDTO> response = restTemplate.exchange(
                uriBuilder,
                HttpMethod.GET,
                null,
                AladinBookDTO.class
        );

        return response.getBody();
    }

    public void getPubDateHandler(List<AladinItems> itemsList) {

        for (AladinItems items : itemsList) {

            String year = items.getPubDate().substring(0, 4);
            String month = items.getPubDate().substring(5,7);
            items.setYear(year);
            items.setMonth(month);
        }
    }

    public void getAuthorsHandler(List<AladinItems> itemsList) {

        for (AladinItems items : itemsList) {
            String[] split = items.getAuthor().split(",");
            List<String> collect = Arrays.stream(split)
                    .map(String::trim)
                    .collect(Collectors.toList());

            itemValid_Contains(items, collect);
            itemValid_Empty(items, collect);
        }
    }

    // 해당 메서드에서는 prefix가 붙어있을경우 넣어 데이터삽입
    private void itemValid_Contains(AladinItems Items, List<String> collect) {

        for (String item : collect) {
            if (item.contains("(지은이)")) {
                String replace = item.replace(" (지은이)", "");
                Items.setAuthorTypeAuthor(replace);
            }
            if (item.contains("(옮긴이)")) {
                String replace = item.replace(" (옮긴이)", "");
                Items.setAuthorTypeTranslator(replace);
            }
        }
    }

    // 해당 메서드에서는 prefix가 붙어있지않아 없음인경우를 확인하고 데이터삽입
    private void itemValid_Empty(AladinItems Items, List<String> collect) {
        int counting = 0;
        for (String item : collect) {

            if (Items.getAuthorTypeAuthor().equals("없음") && counting == 0) {
                Items.setAuthorTypeAuthor(item);
            }

            if (Items.getAuthorTypeTranslator().equals("없음") && counting == 1) {
                Items.setAuthorTypeTranslator(item);
            }
            counting++;
        }
    }
}