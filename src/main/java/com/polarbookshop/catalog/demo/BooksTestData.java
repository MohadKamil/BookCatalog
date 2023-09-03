package com.polarbookshop.catalog.demo;

import com.polarbookshop.catalog.domain.Book;
import com.polarbookshop.catalog.domain.BooksRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Profile("TestData")
@Component
public class BooksTestData {

    private final BooksRepository repository;

    public BooksTestData(BooksRepository repository) {
        this.repository = repository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void seedBooks() {
        repository.deleteAll();
        var books = List.of(
                Book.of("1","Thinking, Fast and Slow","Daniel Kahneman",44),
                Book.of("2","Learning Domain Driven Design","Vlad Khononov",100)
        );

        repository.saveAll(books);
    }
}
