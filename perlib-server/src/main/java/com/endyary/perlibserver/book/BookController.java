package com.endyary.perlibserver.book;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Exposes REST API methods for book management
 *
 * @author Nenad Dramicanin
 */
@RestController
@RequestMapping("/api/")
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    /**
     * Retrieves all existing books
     *
     * @return the list of all books
     */
    @GetMapping("/books")
    public List<Book> getAllBooks() {
        logger.info("Get all");
        return bookService.getAll();
    }

    /**
     * Creates a new book
     *
     * @param book the book data
     * @return the created book
     */
    @PostMapping("/books")
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {
        logger.info("Create {}", book.toString());
        book = bookService.save(book);
        return ResponseEntity.ok(book);
    }

    /**
     * Retrieves an existing book
     *
     * @param id the book id
     * @return the book data
     */
    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        logger.info("Get by ID = {}", id);
        Book book = bookService.getById(id);
        return book != null ?
                ResponseEntity.ok(book) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Edits an existing book
     *
     * @param id          the book id
     * @param updatedBook the changed book values
     * @return the updated book
     */
    @PutMapping("/books/{id}")
    public ResponseEntity<Book> editBook(@PathVariable Long id, @Valid @RequestBody Book updatedBook) {
        logger.info("Edit by ID = {}, {}", id, updatedBook.toString());
        Book book = bookService.update(id, updatedBook);
        return book != null ?
                ResponseEntity.ok(book) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Removes an existing book
     *
     * @param id the book id
     */
    @DeleteMapping("/books/{id}")
    public ResponseEntity<Long> deleteBook(@PathVariable Long id) {
        logger.info("Delete by ID = {}", id);
        boolean isDeleted = bookService.delete(id);
        return isDeleted ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
