package com.polarbookshop.catalog.persistence;

import com.polarbookshop.catalog.domain.Book;
import com.polarbookshop.catalog.domain.BooksRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryBookRepository implements BooksRepository {

    private static final Map<String,Book> store = new ConcurrentHashMap<>();

    @Override
    public List<Book> getBooks() {
        return store.values().stream().toList();
    }

    @Override
    public Optional<Book> getBook(String isbn) {
        return Optional.ofNullable(store.get(isbn));
    }

    @Override
    public void deleteBook(String isbn) {
        store.remove(isbn);
    }

    @Override
    public Book saveBook(Book book) {
        store.put(book.isbn(),book);
        return book;
    }

    @Override
    public boolean isbnExists(String isbn) {
        return store.containsKey(isbn);
    }
}
