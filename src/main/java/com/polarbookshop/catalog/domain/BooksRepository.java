package com.polarbookshop.catalog.domain;

import java.util.List;
import java.util.Optional;

public interface BooksRepository {
    List<Book> getBooks();

    Optional<Book> getBook(String isbn);

    void deleteBook(String isbn);

    Book saveBook(Book book);

    boolean isbnExists(String isbn);
}
