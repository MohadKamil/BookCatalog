package com.polarbookshop.catalog;

import com.polarbookshop.catalog.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class CatalogApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:15.4");

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
