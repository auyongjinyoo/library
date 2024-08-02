package com.auyongjinyoo.library.service;

import com.auyongjinyoo.library.model.Book;
import com.auyongjinyoo.library.model.Borrower;
import com.auyongjinyoo.library.repo.BookRepository;
import com.auyongjinyoo.library.repo.BorrowerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class LibraryServiceTests {
    @Autowired
    private LibraryService libraryService;

    @Autowired
    private BorrowerRepository borrowerRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testRegisterBorrower() {
        Borrower borrower = new Borrower();
        borrower.setName("Test Borrower");
        borrower.setEmail("test.borrower" + System.currentTimeMillis() +"@library.com");
        Borrower savedBorrower = libraryService.registerBorrower(borrower);
        assertNotNull(savedBorrower.getId());
    }

    @Test
    public void testRegisterBook() {
        Book book = new Book();
        book.setIsbn("1234567890");
        book.setTitle("Sample Book");
        book.setAuthor("Author Name");
        String result = libraryService.registerBook(book);
        assertNotNull(result);
    }
}
