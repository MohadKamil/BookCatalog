package com.polarbookshop.catalog.api;

import com.polarbookshop.catalog.domain.Book;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.time.Instant;

@JsonTest
public class BooksJsonTests {

    @Autowired
    private JacksonTester<Book> jacksonTester;

    @Test
    public void testBookSerialization() throws Exception {

        var book = new  Book(1L,"1","Thinking,Fast and Slow","Daniel Kahneman",40,Instant.EPOCH,Instant.EPOCH,1);
        var json = jacksonTester.write(book);

        Approvals.verify(json);
    }
}
