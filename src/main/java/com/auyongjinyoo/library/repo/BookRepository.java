package com.auyongjinyoo.library.repo;

import com.auyongjinyoo.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface  BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByIsbn(String isbn);
}
