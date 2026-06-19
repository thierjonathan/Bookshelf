package com.project.bookshelf.book;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "google_books_id", nullable = false, unique = true, length = 50)
    private String googleBooksId;

    @Column(nullable = false, length = 500)
    private String title;

    @Column(length = 1000)
    private String authors;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "thumbnail_url", length = 2000)
    private String thumbnailUrl;

    @Column(name = "isbn_10", length = 20)
    private String isbn10;

    @Column(name = "isbn_13", length = 20)
    private String isbn13;

    @Column(name = "page_count")
    private Integer pageCount;

    @Column(name = "published_date", length = 20)
    private String publishedDate;

    @Column(name = "cached_at", nullable = false)
    private LocalDateTime cachedAt;

    @PrePersist
    @PreUpdate
    private void updateCachedAt() {
        cachedAt = LocalDateTime.now();
    }
}