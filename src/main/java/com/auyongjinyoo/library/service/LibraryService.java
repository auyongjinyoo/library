package com.auyongjinyoo.library.service;

import com.auyongjinyoo.library.api.ResponseCodeMessage;
import com.auyongjinyoo.library.model.Book;
import com.auyongjinyoo.library.model.Borrower;
import com.auyongjinyoo.library.repo.BookRepository;
import com.auyongjinyoo.library.repo.BorrowerRepository;
import jakarta.persistence.OptimisticLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryService {
    @Autowired
    private BorrowerRepository borrowerRepository;

    @Autowired
    private BookRepository bookRepository;

    public int registerBorrower(String strName, String strEmail) {
        List<Borrower> existingBorrower = borrowerRepository.findByEmail(strEmail);
        if (!existingBorrower.isEmpty()) {
                return ResponseCodeMessage.BORROWER_EMAIL_DUPLICATE;
        }
        Borrower borrower = new Borrower();
        borrower.setName(strName);
        borrower.setEmail(strEmail);
        borrowerRepository.save(borrower);
        return ResponseCodeMessage.BORROWER_REGISTER_SUCCESS;
    }

    public int registerBook(String strIsbn, String strAuthor, String strTitle ) {
        // Check if a book with the same ISBN already exists
        List<Book> existingBookWithSameIsbn = bookRepository.findByIsbn(strIsbn);

        if (!existingBookWithSameIsbn.isEmpty()) {
            Book existingBook = existingBookWithSameIsbn.get(0);

            // Ensure that the existing book has the same title and author
            if (!existingBook.getTitle().equals(strTitle) || !existingBook.getAuthor().equals(strAuthor)) {
                return ResponseCodeMessage.BOOK_ISBN_EXIST;
            }
        }

        Book book = new Book();
        book.setIsbn(strIsbn);
        book.setTitle(strAuthor);
        book.setAuthor(strTitle);

        // If no conflict, save the book
        bookRepository.save(book);
        return ResponseCodeMessage.BOOK_REGISTER_SUCCESS;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getBooksByIsbn(String strIsbn) {
        return bookRepository.findByIsbn(strIsbn);
    }

    public List<Book> getBooksByAuthor(String strAuthor) {
        return bookRepository.findByAuthorContaining(strAuthor);
    }
    public List<Book> getBooksByTitle(String strTitle) {
        return bookRepository.findByTitleContaining(strTitle);
    }

    @Transactional
    public int borrowBook(Long borrowerId, Long bookId) throws Exception {
        try {
            Optional<Borrower> borrower = borrowerRepository.findById(borrowerId);
            if (borrower.isEmpty()) {
                return ResponseCodeMessage.BOOK_NOT_FOUND;
            }

            Optional<Book> book = bookRepository.findById(bookId);
            if (book.isEmpty() || book.get().isBorrowed()) {
                return ResponseCodeMessage.BOOK_NOT_AVAILABLE;
            }

            book.get().setBorrowed(true);
            bookRepository.save(book.get());
            return ResponseCodeMessage.BOOK_BORROWED_SUCCESS;
        } catch (OptimisticLockException e) {
            return ResponseCodeMessage.BOOK_NOT_AVAILABLE;
        }
    }

    public int returnBook(Long borrowerId, Long bookId) throws Exception {
        Optional<Borrower> borrower = borrowerRepository.findById(borrowerId);
        if (borrower.isEmpty()) {
            return ResponseCodeMessage.BORROWER_NOT_FOUND;
        }

        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isEmpty() || !book.get().isBorrowed()) {
            return ResponseCodeMessage.BOOK_RETURN_FAILURE;
        }

        book.get().setBorrowed(false);
        bookRepository.save(book.get());
        return ResponseCodeMessage.BOOK_RETURN_SUCCESS;
    }
}
