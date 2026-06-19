package com.project.bookshelf.integration.google;

import com.project.bookshelf.book.Book;
import com.project.bookshelf.common.exception.ResourceNotFoundException;
import com.project.bookshelf.integration.google.dto.GoogleBookItem;
import com.project.bookshelf.integration.google.dto.GoogleBooksResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoogleBooksService {

    private final GoogleBooksClient googleBooksClient;

    public GoogleBooksService(GoogleBooksClient googleBooksClient) {
        this.googleBooksClient = googleBooksClient;
    }

    public List<Book> search(String query) {
        GoogleBooksResponse response = googleBooksClient.search(query, 20);
        if (response == null || response.items() == null) return List.of();
        return response.items().stream()
                .map(this::toBook)
                .toList();
    }

    public Book fetchById(String googleBooksId) {
        GoogleBookItem item = googleBooksClient.getById(googleBooksId);
        if (item == null) {
            throw new ResourceNotFoundException("Book not found on Google Books: " + googleBooksId);
        }
        return toBook(item);
    }

    private Book toBook(GoogleBookItem item) {
        Book book = new Book();
        book.setGoogleBooksId(item.id());

        GoogleBookItem.VolumeInfo info = item.volumeInfo();
        if (info == null) return book;

        book.setTitle(info.title() != null ? info.title() : "Unknown Title");
        book.setDescription(info.description());
        book.setPageCount(info.pageCount());
        book.setPublishedDate(info.publishedDate());

        if (info.authors() != null) {
            book.setAuthors(String.join(", ", info.authors()));
        }
        if (info.imageLinks() != null) {
            book.setThumbnailUrl(info.imageLinks().thumbnail());
        }
        if (info.industryIdentifiers() != null) {
            info.industryIdentifiers().forEach(identifier -> {
                if ("ISBN_10".equals(identifier.type())) book.setIsbn10(identifier.identifier());
                if ("ISBN_13".equals(identifier.type())) book.setIsbn13(identifier.identifier());
            });
        }

        return book;
    }
}
