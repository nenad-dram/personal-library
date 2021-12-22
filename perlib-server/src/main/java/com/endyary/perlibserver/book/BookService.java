package com.endyary.perlibserver.book;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Manages book data and interacts with the repository
 *
 * @author Nenad Dramicanin
 */
@Service
@RequiredArgsConstructor
public class BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    private final BookRepository bookRepository;

    /**
     * Saves a book object (sets the date values prior).
     *
     * @param book Book object to be saved
     * @return saved book
     */
    public Book save(Book book) {
        if (book.getCreatedDate() == null) {
            book.setCreatedDate(LocalDateTime.now());
        }
        book.setLastModifiedDate(LocalDateTime.now());
        book = bookRepository.save(book);
        logger.info("Resource saved (id = {})", book.getId());
        return book;
    }

    /**
     * Gets a Book by the provided ID, if exists, otherwise null value.
     *
     * @param id the book id
     * @return the book data
     */
    public Book getById(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        logger.info("Resource exists (id = {}): {}", id, bookOptional.isPresent());
        return bookOptional.orElse(null);
    }

    /**
     * Deletes a Book by the provided ID, if exists.
     * Returns false if a book doesn't exist, otherwise true.
     *
     * @param id the book id
     * @return the operation outcome
     */
    public boolean delete(Long id) {
        Book book = getById(id);
        if (book != null) {
            bookRepository.delete(book);
            logger.info("Resource deleted (id = {})", book.getId());
            return true;
        }
        return false;
    }

    /**
     * Finds books by the provided parameters (both book values and pagination values)
     *
     * @param name     Book's name
     * @param author   Book's author
     * @param language Book's language
     * @param page     Number of result's page
     * @param size     Number of items on the page
     * @param sorts    Array of sort by parameters
     * @return List of {@link Page} books
     */
    public Page<Book> find(String name, String author, String language, Integer page, Integer size, String[] sorts) {
        // Set query Specification
        Specification<Book> nameSpec = BookSpecification.nameLike(name);
        Specification<Book> authorSpec = BookSpecification.authorLike(author);
        Specification<Book> languageSpec = BookSpecification.languageEquals(language);
        Specification<Book> spec = Specification.where(nameSpec).and(authorSpec).and(languageSpec);

        // Set query Order
        List<Sort.Order> orders = getSortOrders(sorts);

        Pageable paging = PageRequest.of(page, size, Sort.by(orders));

        return bookRepository.findAll(spec, paging);
    }

    /**
     * Updates a book by the provided ID and the
     * changed book data.
     * Returns null if a book by the ID doesn't exist.
     *
     * @param id   the book id
     * @param book the changed book data
     * @return the updated book
     */
    public Book update(Long id, Book book) {
        Book dbBook = getById(id);
        if (dbBook != null) {
            dbBook.setName(book.getName());
            dbBook.setAuthor(book.getAuthor());
            dbBook.setLanguage(book.getLanguage());
            dbBook = save(dbBook);
        }
        return dbBook;
    }

    /**
     * Converts the provided string array or sort elements (both property and directions)
     * into a list of {@link Sort.Order} elements
     *
     * @param sorts Array or sort elements
     * @return list of {@link Sort.Order} elements
     */
    private List<Sort.Order> getSortOrders(String[] sorts) {

        List<Sort.Order> orders = new ArrayList<>();

        /*
         * If there are multiple elements iterate through the whole list
         * For one element: sorts = ["property", "direction"]
         * For multiple elements: sorts = ["property1,direction1", "property2,direction2", ...]
         */
        if (sorts[0].contains(",")) {
            Arrays.stream(sorts).forEach(sortOrder -> {
                String[] sort = sortOrder.split(",");
                orders.add(new Sort.Order("desc".equals(sort[1]) ? Sort.Direction.DESC : Sort.Direction.ASC, sort[0]));
            });
        } else {
            orders.add(new Sort.Order("desc".equals(sorts[1]) ? Sort.Direction.DESC : Sort.Direction.ASC, sorts[0]));
        }
        return orders;
    }

}
