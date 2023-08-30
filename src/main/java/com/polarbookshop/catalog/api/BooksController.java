package com.polarbookshop.catalog.api;

import com.polarbookshop.catalog.domain.Book;
import com.polarbookshop.catalog.domain.BookService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping
    public Book createBook(@Valid @RequestBody Book book) {
        return bookService.addBook(book);
    }
}
