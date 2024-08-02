package com.auyongjinyoo.library.dto;

public class BorrowRequest {
    private Long borrowerId;
    private Long bookId;

    // Getters and setters
    public Long getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(Long borrowerId) {
        this.borrowerId = borrowerId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}
