package com.auyongjinyoo.library.repo;

import com.auyongjinyoo.library.model.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  BorrowerRepository extends JpaRepository<Borrower, Long> {
}
