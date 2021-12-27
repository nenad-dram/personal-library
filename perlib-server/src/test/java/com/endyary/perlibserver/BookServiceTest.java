package com.endyary.perlibserver;

import com.endyary.perlibserver.book.Book;
import com.endyary.perlibserver.book.BookRepository;
import com.endyary.perlibserver.book.BookService;
import com.endyary.perlibserver.misc.Language;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains unit tests for {@link BookService}
 *
 * @author Nenad Dramicanin
 */
@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    public void sortOrder_singleElement_converted() {
        String[] sortOrderIn = {"id,asc"};
        List<Sort.Order> expectedOrders = new ArrayList<>();
        expectedOrders.add(new Sort.Order(Sort.Direction.ASC, "id"));

        List<Sort.Order> actualOrders = bookService.getSortOrders(sortOrderIn);

        SortOrderAssert.assertThat(actualOrders).equals(expectedOrders);
    }

    @Test
    public void sortOrder_multipleElements_converted() {
        String[] sortOrderIn = {"name,asc", "author,desc"};
        List<Sort.Order> expectedOrders = new ArrayList<>();
        expectedOrders.add(new Sort.Order(Sort.Direction.ASC, "name"));
        expectedOrders.add(new Sort.Order(Sort.Direction.DESC, "author"));

        List<Sort.Order> actualOrders = bookService.getSortOrders(sortOrderIn);

        SortOrderAssert.assertThat(actualOrders).equals(expectedOrders);
    }

    @Test
    public void save_createNew_createdAndModifiedDateAdded() {
        Book book = new Book();
        book.setName("Book name");
        book.setAuthor("Book author");
        book.setLanguage(Language.ENGLISH.name());

        Mockito.when(bookRepository.save(Mockito.any(Book.class))).then(AdditionalAnswers.returnsFirstArg());
        Book actualBook = bookService.save(book);

        SoftAssertions bookAssert = new SoftAssertions();
        bookAssert.assertThat(actualBook.getCreatedDate()).isNotNull();
        bookAssert.assertThat(actualBook.getLastModifiedDate()).isNotNull();
        bookAssert.assertAll();

    }
}
