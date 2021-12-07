package com.endyary.perlibserver.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book save(Book book) {
        if (book.getCreatedDate() == null) {
            book.setCreatedDate(LocalDateTime.now());
        }
        book.setLastModifiedDate(LocalDateTime.now());
        return bookRepository.save(book);
    }

    public Book getById(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        return bookOptional.orElse(null);
    }

    public boolean delete(Long id) {
        boolean isDeleted = false;
        Book book = getById(id);
        if (book != null) {
            bookRepository.delete(book);
            isDeleted = true;
        }
        return isDeleted;
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

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
