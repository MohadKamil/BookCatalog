package com.polarbookshop.catalog.persistence;

import com.polarbookshop.catalog.config.PersistenceConfig;
import com.polarbookshop.catalog.domain.Book;
import com.polarbookshop.catalog.domain.BooksRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest
@Import(PersistenceConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
public class BookRepositoryTests {

    @Autowired
    private BooksRepository booksRepository;
    @Autowired
    private JdbcAggregateTemplate jdbcAggregateTemplate;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:15.4");


    @Test
    public void findBookByIsbnShouldReturnMatchingBook() {

        var isbn = "1";
        var book = Book.of(isbn,"Title","author",1);
        jdbcAggregateTemplate.save(book);

        var retrievedBook = booksRepository.findByIsbn(isbn);

        assertThat(retrievedBook).isNotNull();
    }
}
