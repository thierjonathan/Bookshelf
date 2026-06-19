package com.project.bookshelf.integration.google;

import com.project.bookshelf.integration.google.dto.GoogleBookItem;
import com.project.bookshelf.integration.google.dto.GoogleBooksResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class GoogleBooksClient {

    private static final String BASE_URL = "https://www.googleapis.com/books/v1";

    private final RestTemplate restTemplate;

    @Value("${google.books.api-key:}")
    private String apiKey;

    public GoogleBooksClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public GoogleBooksResponse search(String query, int maxResults) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(BASE_URL + "/volumes")
                .queryParam("q", query)
                .queryParam("maxResults", maxResults);

        if (apiKey != null && !apiKey.isBlank()) {
            builder.queryParam("key", apiKey);
        }

        return restTemplate.getForObject(builder.toUriString(), GoogleBooksResponse.class);
    }

    public GoogleBookItem getById(String googleBooksId) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(BASE_URL + "/volumes/" + googleBooksId);

        if (apiKey != null && !apiKey.isBlank()) {
            builder.queryParam("key", apiKey);
        }

        return restTemplate.getForObject(builder.toUriString(), GoogleBookItem.class);
    }
}