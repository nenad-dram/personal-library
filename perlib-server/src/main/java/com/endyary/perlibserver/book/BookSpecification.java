package com.endyary.perlibserver.book;

import org.springframework.data.jpa.domain.Specification;

/**
 * Defines possible Specification criteria for the book filtering
 *
 * @author Nenad Dramicanin
 */
public class BookSpecification {

    /**
     * Specifies a criteria for the "name" parameter.
     * Criteria: the "name" must contain the provided value.
     * The criteria will be ignored if parameter hasn't been provided.
     *
     * @param name Book's name part
     * @return A new criteria for the CriteriaBuilder
     */
    public static Specification<Book> nameLike(String name) {
        return (root, query, builder) ->
                name == null ?
                        builder.conjunction() :
                        builder.like(builder.upper(root.get("name")), "%" + name.toUpperCase() + "%");
    }

    /**
     * Specifies a criteria for the "author" parameter.
     * Criteria: the "author" must contain the provided value.
     * The criteria will be ignored if parameter hasn't been provided.
     *
     * @param author Book's author part
     * @return A new criteria for the CriteriaBuilder
     */
    public static Specification<Book> authorLike(String author) {
        return (root, query, builder) ->
                author == null ?
                        builder.conjunction() :
                        builder.like(builder.upper(root.get("author")), "%" + author.toUpperCase() + "%");
    }

    /**
     * Specifies a criteria for the "language" parameter.
     * Criteria: the "language" must match the provided value.
     * The criteria will be ignored if parameter hasn't been provided.
     *
     * @param language Book's language
     * @return A new criteria for the CriteriaBuilder
     */
    public static Specification<Book> languageEquals(String language) {
        return (root, query, builder) ->
                language == null ?
                        builder.conjunction() :
                        builder.equal(root.get("language"), language);
    }
}
