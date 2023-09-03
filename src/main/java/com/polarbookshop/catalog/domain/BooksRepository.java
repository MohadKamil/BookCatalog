package com.polarbookshop.catalog.domain;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface BooksRepository extends ListCrudRepository<Book,Long> {

    Optional<Book> findByIsbn(String isbn);
    boolean existsByIsbn(String isbn);

    @Transactional
    @Modifying
    @Query("DELETE FROM book where isbn = :isbn")
    void deleteByIsbn(String isbn);
}
