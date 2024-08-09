package com.auyongjinyoo.library.repo;

import com.auyongjinyoo.library.model.Book;
import com.auyongjinyoo.library.model.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface  BorrowerRepository extends JpaRepository<Borrower, Long> {
    List<Borrower> findByEmail(String email);
}
