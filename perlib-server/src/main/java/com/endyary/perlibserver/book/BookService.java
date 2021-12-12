package com.endyary.perlibserver.book;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Manages book data and interacts with the repository
 *
 * @author Nenad Dramicanin
 */
@Service
public class BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    @Autowired
    private BookRepository bookRepository;

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
     * Gets all books
     *
     * @return List of books
     */
    public List<Book> getAll() {
        return bookRepository.findAll();
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

}
