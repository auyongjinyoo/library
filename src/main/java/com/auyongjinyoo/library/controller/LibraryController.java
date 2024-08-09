package com.auyongjinyoo.library.controller;

import com.auyongjinyoo.library.api.ApiResponse;
import com.auyongjinyoo.library.api.ResponseCodeMessage;
import com.auyongjinyoo.library.dto.BookDTO;
import com.auyongjinyoo.library.dto.BorrowRequest;
import com.auyongjinyoo.library.dto.BorrowerDTO;
import com.auyongjinyoo.library.model.Book;
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
    public ResponseEntity<?> registerBorrower(@Valid @RequestBody BorrowerDTO borrowerDTO) {
        int responseCode = libraryService.registerBorrower(borrowerDTO.getName(), borrowerDTO.getEmail());
        return new ResponseEntity<>(new ApiResponse(
                responseCode,
                ResponseCodeMessage.getMessage(responseCode),
                borrowerDTO
        ), HttpStatus.OK);
    }

    @PostMapping("/books")
    public ResponseEntity<?> registerBook(@Valid @RequestBody BookDTO bookDTO) {
        int responseCode = libraryService.registerBook(bookDTO.getIsbn(),bookDTO.getAuthor(), bookDTO.getTitle());
        return new ResponseEntity<>(new ApiResponse(
                responseCode,
                ResponseCodeMessage.getMessage(responseCode),
                bookDTO
        ), HttpStatus.OK);
    }

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return libraryService.getAllBooks();
    }

    @GetMapping("/books/byIsbn")
    public List<Book> getBooksByIsbn(@Valid @RequestBody BookDTO bookDTO) {
        return libraryService.getBooksByIsbn(bookDTO.getIsbn());
    }

    @GetMapping("/books/byAuthor")
    public List<Book> getBooksByAuthor(@Valid @RequestBody BookDTO bookDTO) {
        return libraryService.getBooksByAuthor(bookDTO.getAuthor());
    }

    @GetMapping("/books/byTitle")
    public List<Book> getBooksByTitle(@Valid @RequestBody BookDTO bookDTO) {
        return libraryService.getBooksByTitle(bookDTO.getTitle());
    }

    @PostMapping("/borrow")
    public ResponseEntity<?> borrowBook(@RequestBody BorrowRequest borrowRequest) throws Exception {
        try {
            int responseCode = libraryService.borrowBook(borrowRequest.getBorrowerId(), borrowRequest.getBookId());
            return new ResponseEntity<>(new ApiResponse(
                    responseCode,
                    ResponseCodeMessage.getMessage(responseCode),
                    borrowRequest
            ), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.ok("Error: " + e.getMessage());
        }
    }

    @PostMapping("/return")
    public ResponseEntity<?> returnBook(@RequestBody BorrowRequest borrowRequest) throws Exception {
        try {
            int responseCode= libraryService.returnBook(borrowRequest.getBorrowerId(), borrowRequest.getBookId());
            return new ResponseEntity<>(new ApiResponse(
                    responseCode,
                    ResponseCodeMessage.getMessage(responseCode),
                    borrowRequest
            ), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.ok("Error: " + e.getMessage());
        }
    }
}
