package com.auyongjinyoo.library.service;

import com.auyongjinyoo.library.dto.BookDTO;
import com.auyongjinyoo.library.dto.BorrowerDTO;
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
        BorrowerDTO borrowerDTO = new BorrowerDTO();
        borrowerDTO.setName("Test Borrower");
        borrowerDTO.setEmail("test.borrower" + System.currentTimeMillis() +"@library.com");
        int savedBorrower = libraryService.registerBorrower(borrowerDTO.getName(),borrowerDTO.getEmail());
        assertNotNull(savedBorrower);
    }

    @Test
    public void testRegisterBook() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setIsbn("1234567890");
        bookDTO.setTitle("Sample Book");
        bookDTO.setAuthor("Author Name");
        int result = libraryService.registerBook(bookDTO.getIsbn(),bookDTO.getAuthor(),bookDTO.getTitle());
        assertNotNull(result);
    }
}
