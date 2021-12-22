package com.endyary.perlibserver.book;

import com.endyary.perlibserver.misc.Language;
import com.endyary.perlibserver.misc.ValueOfEnum;
import lombok.Data;
import lombok.ToString;

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
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    @ToString.Exclude
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
    @ToString.Exclude
    private LocalDateTime createdDate;
    @ToString.Exclude
    private LocalDateTime lastModifiedDate;
}
