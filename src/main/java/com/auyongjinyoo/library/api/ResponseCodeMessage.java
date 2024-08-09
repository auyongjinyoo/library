package com.auyongjinyoo.library.api;

import java.util.HashMap;
import java.util.Map;

public class ResponseCodeMessage {
    // Constants for response codes
    public static final int SUCCESS = 1000;
    public static final int ERROR = 1001;
    public static final int BORROWER_REGISTER_SUCCESS = 2000;
    public static final int BORROWER_NOT_FOUND = 2001;
    public static final int BORROWER_EMAIL_DUPLICATE = 2002;
    public static final int BOOK_REGISTER_SUCCESS = 3000;
    public static final int BOOK_ISBN_EXIST = 3001;
    public static final int BOOK_NOT_FOUND = 3002;
    public static final int BOOK_BORROWED_SUCCESS = 3003;
    public static final int BOOK_NOT_AVAILABLE = 3004;
    public static final int BOOK_RETURN_SUCCESS = 3005;
    public static final int BOOK_RETURN_FAILURE = 3006;


    private static final Map<Integer, String> codeMessageMap = new HashMap<>();
    static {
        // Initialize the map with response codes and corresponding messages
        codeMessageMap.put(SUCCESS, "Operation completed successfully.");
        codeMessageMap.put(ERROR, "ERROR");
        codeMessageMap.put(BORROWER_NOT_FOUND, "The borrower does not exist.");
        codeMessageMap.put(BORROWER_EMAIL_DUPLICATE, "Borrower already exists with a same email.");
        codeMessageMap.put(BORROWER_REGISTER_SUCCESS, "Borrower registered successfully");
        codeMessageMap.put(BOOK_REGISTER_SUCCESS, "Book registered successfully");
        codeMessageMap.put(BOOK_ISBN_EXIST, "ISBN already exists with a different title or author");
        codeMessageMap.put(BOOK_NOT_FOUND, "The requested book was not found.");
        codeMessageMap.put(BOOK_BORROWED_SUCCESS, "Book borrowed successfully.");
        codeMessageMap.put(BOOK_NOT_AVAILABLE, "The book is not available for borrowing.");
        codeMessageMap.put(BOOK_RETURN_SUCCESS, "Book returned successfully.");
        codeMessageMap.put(BOOK_RETURN_FAILURE, "Book not borrowed.");
        // Add more codes and messages as needed
    }

    public static String getMessage(int code) {
        return codeMessageMap.getOrDefault(code, "Unknown Error");
    }
}
