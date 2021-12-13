package com.endyary.perlibserver.book;

import com.endyary.perlibserver.misc.Language;
import com.endyary.perlibserver.misc.ValueOfEnum;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * Model for a Book data
 *
 * @author Nenad Dramicanin
 */
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @NotBlank()
    @Column(nullable = false)
    private String name;

    @NotBlank()
    @Column(nullable = false)
    private String author;

    @ValueOfEnum(enumClass = Language.class)
    @Column(nullable = false)
    private String language;

    @Column(updatable = false, nullable = false)
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return String.format("{name = %s, author = %s, language = %s}", this.name, this.author, this.language);
    }
}
