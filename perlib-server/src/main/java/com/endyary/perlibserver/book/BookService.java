package com.endyary.perlibserver.book;

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
        return bookRepository.save(book);
    }

    /**
     * Gets a Book by the provided ID, if exists, otherwise null value.
     *
     * @param id the book id
     * @return the book data
     */
    public Book getById(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        return bookOptional.orElse(null);
    }

    /**
     * Deletes a Book by the provided ID, if exists.
     * The operation outcome is negative (false) if the book doesn't
     * exist, otherwise positive (true).
     *
     * @param id the book id
     * @return the operation outcome
     */
    public boolean delete(Long id) {
        Book book = getById(id);
        if (book != null) {
            bookRepository.delete(book);
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
