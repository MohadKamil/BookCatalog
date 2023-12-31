package com.polarbookshop.catalog.domain;

import com.polarbookshop.catalog.domain.exceptions.BookExistsException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BooksRepository booksRepository;

    public BookService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> getBooks() {
        return booksRepository.findAll();
    }

    public Optional<Book> getBook(String isbn) {
        return booksRepository.findByIsbn(isbn);
    }

    public void deleteBook(String isbn) {
        booksRepository.deleteByIsbn(isbn);
    }

    public Book addBook(Book book) {
        if(booksRepository.existsByIsbn(book.isbn())) {
            throw new BookExistsException(book.isbn());
        }

        return saveBook(book);
    }

    //Since Id and version are server side maintained, they shouldn't be declared in the API contract
    public Book editBook(String isbn, Book book) {
        var optionalBook = booksRepository.findByIsbn(isbn);

        if (optionalBook.isEmpty()) {
            return addBook(book);
        }
        else {
            var currentBook = optionalBook.get();

            var updatedBook = new Book(currentBook.id(),
                    book.isbn(),
                    book.title(),
                    book.author(),
                    book.price(),
                    currentBook.createdDate(),
                    currentBook.lastModifiedDate(),
                    currentBook.version());

            return saveBook(updatedBook);
        }
    }

    private Book saveBook(Book book) {
        return booksRepository.save(book);
    }
}

