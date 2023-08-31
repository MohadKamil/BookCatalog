package com.polarbookshop.catalog.api;

import com.polarbookshop.catalog.domain.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BooksController.class)
public class BookControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    public void whenBookDoesNotExist_404ResponseShouldBeReturned() throws Exception {
        var isbn = "1";

        given(bookService.getBook(isbn)).willReturn(Optional.empty());

        var response = mockMvc.perform(get("/books/" + isbn));

        response.andExpect(status().isNotFound());
    }
}
