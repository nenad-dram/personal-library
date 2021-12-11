package com.endyary.perlibserver.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Exposes REST API methods for book management
 *
 * @author Nenad Dramicanin
 */
@RestController
@RequestMapping("/api/")
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     * Retrieves all existing books
     *
     * @return the list of all books
     */
    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookService.getAll();
    }

    /**
     * Creates a new book
     *
     * @param book the book data
     * @return the created book
     */
    @PostMapping("/books")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
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
    public ResponseEntity<Book> editBook(@PathVariable Long id, @RequestBody Book updatedBook) {
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
        boolean isDeleted = bookService.delete(id);
        return isDeleted ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
