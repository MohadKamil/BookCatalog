package com.polarbookshop.catalog.api;

import com.polarbookshop.catalog.domain.Book;
import com.polarbookshop.catalog.domain.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@Valid @RequestBody Book book) {
        return bookService.addBook(book);
    }

    @GetMapping("{isbn}")
    public ResponseEntity<Book> getBook(@PathVariable String isbn) {
        var optionalBook = bookService.getBook(isbn);
        return ResponseEntity.of(optionalBook);
    }
}
