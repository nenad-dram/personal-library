package com.endyary.perlibserver.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interacts with the Database via JpaRepository
 *
 * @author Nenad Dramicanin
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
