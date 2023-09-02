package com.polarbookshop.catalog.domain;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

public record Book(
        @Id
        Long id,
        @NotEmpty(message = "books isbn is required")
        String isbn,
        @NotEmpty(message = "title is required")
        String title,
        @NotEmpty(message = "book author is required")
        String author,
        @Positive(message = "book price should be greater than zero")
        double price,
        @Version
        int version) {


        public static Book of(
                String isbn, String title, String author, double price
        ) {
                return new Book(
                        null, isbn, title, author, price, 0
                );
        }
}
