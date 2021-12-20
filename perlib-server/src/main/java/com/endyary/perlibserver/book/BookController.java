package com.endyary.perlibserver.book;

import com.endyary.perlibserver.misc.Language;
import com.endyary.perlibserver.misc.ValueOfEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Exposes REST API methods for book management
 *
 * @author Nenad Dramicanin
 */
@RestController
@RequestMapping("/api/")
@Validated
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    /**
     * Retrieves books for the provided parameters
     *
     * @return the list of books
     */
    @GetMapping("/books")
    public ResponseEntity<Map<String, Object>> getBooks(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) @ValueOfEnum(enumClass = Language.class) String language,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "${spring.data.web.pageable.default-page-size}") Integer size,
            @RequestParam(defaultValue = "${spring.data.web.sort.sort-parameter}") String[] sorts) {

        logger.info("Get books: name = {}, author = {}, language = {}, page = {}, size = {}, sort = {}",
                name, author, language, page, size, sorts);

        Page<Book> pageResult = bookService.find(name, author, language, page, size, sorts);

        Map<String, Object> response = new HashMap<>();
        response.put("books", pageResult.getContent());
        response.put("totalItems", pageResult.getTotalElements());
        response.put("currentPage", pageResult.getNumber());
        response.put("totalPages", pageResult.getTotalPages());

        return ResponseEntity.ok(response);
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
