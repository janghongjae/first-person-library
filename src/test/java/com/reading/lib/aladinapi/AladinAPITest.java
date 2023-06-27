package com.reading.lib.aladinapi;

import com.reading.lib.global.aladinapi.AladinBookDTO;
import com.reading.lib.global.aladinapi.AladinItems;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@Slf4j
public class AladinAPITest {

    @Value("${aladin.ttbKey}")
    private String ttbKey;

    private final String apiUrl = "http://www.aladin.co.kr";
    private final String path = "/ttb/api/ItemSearch.aspx";

    // Cover BIG - https://image.aladin.co.kr/product/21573/93/cover/8983927623_2.jpg
    // Cover MID - https://image.aladin.co.kr/product/21573/93/coversum/8983927623_2.jpg
    // Cover SMALL - https://image.aladin.co.kr/product/21573/93/coveroff/8983927623_2.jpg

    private ResponseEntity<AladinBookDTO> aladinAPI() {
        String keyword = "해리포터";

        RestTemplate restTemplate = new RestTemplate();

        URI uriBuilder = UriComponentsBuilder.fromUriString(apiUrl)
                .path(path)
                .queryParam("ttbkey", ttbKey)
                .queryParam("Query", keyword)
                .queryParam("QueryType", "Title")
                .queryParam("MaxResults", 10)
                .queryParam("start", 1)
                .queryParam("SearchTarget", "Book")
                .queryParam("output", "js")
                .queryParam("Version", "20131101")
                .queryParam("Cover", "Small")
                .encode().build().toUri();

        ResponseEntity<AladinBookDTO> response = restTemplate.exchange(
                uriBuilder,
                HttpMethod.GET,
                null,
                AladinBookDTO.class
        );
        return response;
    }

    @Test
    void aladinAPIResponseTest() {
        ResponseEntity<AladinBookDTO> result = aladinAPI();
        Assertions.assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("알라딘 API Authors 추출")
    void apiAuthorsTransfer() {
        ResponseEntity<AladinBookDTO> aladinAPI = aladinAPI();
        aladinAPI.getBody().getItem().stream().forEach(items -> getAuthorsHandler(items));
        Assertions.assertThat(aladinAPI).isNotNull();
    }

    @Test
    @DisplayName("알라딘 API Custom Authors 추출")
    void apiCustomAuthors() {
        ResponseEntity<AladinBookDTO> aladinAPI = aladinAPI();
        aladinAPI.getBody().getItem().stream().forEach(items -> getAuthorsCustomHandler(items));
    }

    private void getAuthorsCustomHandler(AladinItems Items) {
        List<String> authorsList = new ArrayList<>();
        authorsList.add("J.K. 롤링");
        authorsList.add("강동혁 (옮긴이)");
        authorsList.add("안녕");


        itemValid_Contains(Items, authorsList);
        itemValid_Empty(Items, authorsList);


    }

    private void getAuthorsHandler(AladinItems Items) {
        String[] split = Items.getAuthor().split(",");
        List<String> collect = Arrays.stream(split)
                .map(String::trim)
                .collect(Collectors.toList());


        // 현재 글을 읽어보니깐 많은 Valid가 필요해보입니다 어떤책은 지은이가없고 하나만있고
        // 서버에서 처리하기위해서는 상황을 가정해서 Valid검사를 해야할거같습니다
        itemValid_Contains(Items, collect);
        itemValid_Empty(Items, collect);

    }

    // 해당 메서드에서는 prefix가 붙어있을경우 넣어 데이터삽입
    private void itemValid_Contains(AladinItems Items, List<String> collect) {

        for (String item : collect) {
            if (item.contains("(지은이)")) {
                String replace = item.replace("(지은이)", "");
                Items.setAuthorTypeAuthor(replace);
            }
            if (item.contains("(옮긴이)")) {
                String replace = item.replace("(옮긴이)", "");
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
