package com.polarbookshop.catalog.persistence;

import com.polarbookshop.catalog.config.PersistenceConfig;
import com.polarbookshop.catalog.domain.Book;
import com.polarbookshop.catalog.domain.BooksRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest
@Import(PersistenceConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("integration")
public class BookRepositoryTests {

    @Autowired
    private BooksRepository booksRepository;
    @Autowired
    private JdbcAggregateTemplate jdbcAggregateTemplate;


    @Test
    public void findBookByIsbnShouldReturnMatchingBook() {

        var isbn = "1";
        var book = Book.of(isbn,"Title","author",1);
        jdbcAggregateTemplate.save(book);

        var retrievedBook = booksRepository.findByIsbn(isbn);

        assertThat(retrievedBook).isNotNull();
    }
}
