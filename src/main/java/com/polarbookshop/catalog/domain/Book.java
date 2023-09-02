package com.polarbookshop.catalog.domain;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import java.time.Instant;

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
        @CreatedDate
        Instant createdDate,
        @LastModifiedDate
        Instant lastModifiedData,
        @Version
        int version) {


        public static Book of(
                String isbn, String title, String author, double price
        ) {
                return new Book(
                        null, isbn, title, author, price, Instant.now(),Instant.now(),0
                );
        }
}
