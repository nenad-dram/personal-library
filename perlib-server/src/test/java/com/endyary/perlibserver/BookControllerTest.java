package com.endyary.perlibserver;

import com.endyary.perlibserver.book.Book;
import com.endyary.perlibserver.book.BookController;
import com.endyary.perlibserver.book.BookService;
import com.endyary.perlibserver.misc.Language;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.Map;

/**
 * Contains tests for {@link BookController}
 *
 * @author Nenad Dramicanin
 */
@WebMvcTest(BookController.class)
public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Autowired
    private ObjectMapper objectMapper;

    private Book getMockBook() {
        Book book = new Book();
        book.setName("Book name");
        book.setAuthor("Book author");
        book.setLanguage(Language.ENGLISH.name());
        return book;
    }

    @Test
    protected void getBookById_noAuth_returnOk() throws Exception {
        Book book = getMockBook();
        Mockito.when(bookService.getById(Mockito.any(Long.class))).thenReturn(book);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/books/{id}", 10L))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    protected void deleteBookById_noAuth_returnUnauthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/books/{id}", 10L))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @WithMockUser
    protected void createBook_validRequest_returnBook() throws Exception {
        Book book = getMockBook();
        String expectedResponseBody = objectMapper.writeValueAsString(book);
        Mockito.when(bookService.save(Mockito.any(Book.class))).thenReturn(book);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(expectedResponseBody)).andReturn();
        String actualResponseBody = mvcResult.getResponse().getContentAsString();

        Assertions.assertThat(actualResponseBody).isEqualToIgnoringWhitespace(expectedResponseBody);
    }

    @Test
    @WithMockUser
    protected void editBook_noAuthor_returnError() throws Exception {
        Book book = getMockBook();
        book.setAuthor(null);
        Map<String, String> expectedErrorResponse = new HashMap<>();
        expectedErrorResponse.put("author", "must not be blank");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/books/{id}", 10L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();

        String actualResponseBody = mvcResult.getResponse().getContentAsString();
        String expectedResponseBody = objectMapper.writeValueAsString(expectedErrorResponse);
        Assertions.assertThat(actualResponseBody).isEqualToIgnoringWhitespace(expectedResponseBody);
    }

    @Test
    protected void getLanguages_returnCorrectSize() throws Exception {
        int expectedSize = Language.values().length;

        mockMvc.perform(MockMvcRequestBuilders.get("/api/languages"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(expectedSize)));
    }
}
