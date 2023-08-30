package com.polarbookshop.catalog.domain;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

public record Book(
        @NotEmpty(message = "books isbn is required")
        String isbn,
        @NotEmpty(message = "title is required")
        String title,
        @NotEmpty(message = "book author is required")
        String author,
        @Positive(message = "book price should be greater than zero")
        double price) {
}
