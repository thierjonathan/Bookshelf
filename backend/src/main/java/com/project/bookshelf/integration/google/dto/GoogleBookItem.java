package com.project.bookshelf.integration.google.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GoogleBookItem(
        String id,
        VolumeInfo volumeInfo
) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record VolumeInfo(
            String title,
            List<String> authors,
            String description,
            ImageLinks imageLinks,
            List<IndustryIdentifier> industryIdentifiers,
            Integer pageCount,
            String publishedDate
    ) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record ImageLinks(String thumbnail) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record IndustryIdentifier(String type, String identifier) {}
}