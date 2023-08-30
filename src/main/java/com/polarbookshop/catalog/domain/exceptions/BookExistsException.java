package com.polarbookshop.catalog.domain.exceptions;

public class BookExistsException extends RuntimeException {

    public BookExistsException(String isbn) {
        super("book with isbn: " + isbn + " already exists");
    }
}
