package com.polarbookshop.catalog;

import com.polarbookshop.catalog.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CatalogApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void whenBooksPostIsCalled_SentBookShouldBeSaved() {
        var book = Book.of("1","Thinking, Fast and Slow","Daniel Kahneman",40);

        var response = webTestClient
                .post()
                .uri("/books")
                .bodyValue(book)
                .exchange();

        response.expectStatus()
                .isCreated()
                .expectBody(Book.class)
                .value(b -> {
                   assertThat(b).isNotNull();
                   assertThat(b.isbn()).isEqualTo(book.isbn());
                   assertThat(b.title()).isEqualTo(book.title());
                   assertThat(b.author()).isEqualTo(book.author());
                   assertThat(b.price()).isEqualTo(book.price());
                });
    }

}
