package com.polarbookshop.catalog.api;

import com.polarbookshop.catalog.domain.Book;
import com.polarbookshop.catalog.domain.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BooksController {

    private final BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public Iterable<Book> getBooks() {
        return bookService.getBooks();
    }
}
