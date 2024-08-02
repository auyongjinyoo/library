package com.auyongjinyoo.library.service;

import com.auyongjinyoo.library.model.Book;
import com.auyongjinyoo.library.model.Borrower;
import com.auyongjinyoo.library.repo.BookRepository;
import com.auyongjinyoo.library.repo.BorrowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryService {
    @Autowired
    private BorrowerRepository borrowerRepository;

    @Autowired
    private BookRepository bookRepository;

    public Borrower registerBorrower(Borrower borrower) {
        return borrowerRepository.save(borrower);
    }

    public String registerBook(Book book) {
        // Check if a book with the same ISBN already exists
        List<Book> existingBookWithSameIsbn = bookRepository.findByIsbn(book.getIsbn());

        if (!existingBookWithSameIsbn.isEmpty()) {
            Book existingBook = existingBookWithSameIsbn.get(0);

            // Ensure that the existing book has the same title and author
            if (!existingBook.getTitle().equals(book.getTitle()) || !existingBook.getAuthor().equals(book.getAuthor())) {
                return "ISBN already exists with a different title or author.";
            }
        }

        // If no conflict, save the book
        bookRepository.save(book);
        return "Book registered successfully";
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book borrowBook(Long borrowerId, Long bookId) throws Exception {
        Optional<Borrower> borrower = borrowerRepository.findById(borrowerId);
        if (!borrower.isPresent()) {
            throw new Exception("Borrower not found");
        }

        Optional<Book> book = bookRepository.findById(bookId);
        if (!book.isPresent() || book.get().isBorrowed()) {
            throw new Exception("Book not available");
        }

        book.get().setBorrowed(true);
        return bookRepository.save(book.get());
    }

    public Book returnBook(Long borrowerId, Long bookId) throws Exception {
        Optional<Borrower> borrower = borrowerRepository.findById(borrowerId);
        if (!borrower.isPresent()) {
            throw new Exception("Borrower not found");
        }

        Optional<Book> book = bookRepository.findById(bookId);
        if (!book.isPresent() || !book.get().isBorrowed()) {
            throw new Exception("Book not borrowed");
        }

        book.get().setBorrowed(false);
        return bookRepository.save(book.get());
    }
}
