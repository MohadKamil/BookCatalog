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
        return booksRepository.getBooks();
    }

    public Optional<Book> getBook(String isbn) {
        return booksRepository.getBook(isbn);
    }

    public void deleteBook(String isbn) {
        booksRepository.deleteBook(isbn);
    }

    public Book addBook(Book book) {
        if(booksRepository.isbnExists(book.isbn())) {
            throw new BookExistsException(book.isbn());
        }

        return saveBook(book);
    }

    public Book editBook(String isbn, Book book) {
        var optionalBook = booksRepository.getBook(isbn);

        if (optionalBook.isEmpty()) {
            return addBook(book);
        }
        else {
            var currentBook = optionalBook.get();

            var updatedBook = new Book(currentBook.isbn(),
                    book.title(), book.author(), book.price());

            return saveBook(updatedBook);
        }
    }

    private Book saveBook(Book book) {
        return booksRepository.saveBook(book);
    }
}

