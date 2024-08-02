package com.auyongjinyoo.library.controller;

import com.auyongjinyoo.library.dto.BorrowRequest;
import com.auyongjinyoo.library.model.Book;
import com.auyongjinyoo.library.model.Borrower;
import com.auyongjinyoo.library.service.LibraryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/library")
public class LibraryController {
    @Autowired
    private LibraryService libraryService;

    @PostMapping("/borrowers")
    public Borrower registerBorrower(@Valid @RequestBody Borrower borrower) {
        return libraryService.registerBorrower(borrower);
    }

    @PostMapping("/books")
    public ResponseEntity<String> registerBook(@Valid @RequestBody Book book) {
        String result = libraryService.registerBook(book);
        if (result.startsWith("ISBN already exists")) {
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return libraryService.getAllBooks();
    }

    @PostMapping("/borrow")
    public ResponseEntity<String> borrowBook(@RequestBody BorrowRequest borrowRequest) throws Exception {
        try {
            Book borrowedBook = libraryService.borrowBook(borrowRequest.getBorrowerId(), borrowRequest.getBookId());
            return ResponseEntity.ok("Book borrowed successfully: " + borrowedBook.getTitle());
        } catch (Exception e) {
            return ResponseEntity.ok("Error: " + e.getMessage());
        }
    }

    @PostMapping("/return")
    public ResponseEntity<String> returnBook(@RequestBody BorrowRequest borrowRequest) throws Exception {
        try {
            Book borrowedBook = libraryService.returnBook(borrowRequest.getBorrowerId(), borrowRequest.getBookId());
            return ResponseEntity.ok("Book returned successfully: " + borrowedBook.getTitle());
        } catch (Exception e) {
            return ResponseEntity.ok("Error: " + e.getMessage());
        }
    }
}
