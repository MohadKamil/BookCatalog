package com.polarbookshop.catalog.domain;

import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface BooksRepository extends ListCrudRepository<Book,Long> {

    Optional<Book> findByIsbn(String isbn);
    boolean existsByIsbn(String isbn);
    void deleteByIsbn(String isbn);
}
