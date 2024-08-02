package com.auyongjinyoo.library.controller;

import com.auyongjinyoo.library.service.LibraryService;
import com.auyongjinyoo.library.model.Book;
import com.auyongjinyoo.library.dto.BorrowRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class LibraryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private LibraryService libraryService;

    @InjectMocks
    private LibraryController libraryController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBorrowBookSuccess() throws Exception {
        // Prepare test data
        Book book = new Book();
        book.setId(101L);
        book.setIsbn("1234567890");
        book.setTitle("Sample Book");
        book.setAuthor("Sample Author");

        // Mock the service call
        Mockito.when(libraryService.borrowBook(Mockito.anyLong(), Mockito.anyLong())).thenReturn(book);

        // Create the request body
        String requestBody = "{\"borrowerId\":2,\"bookId\":3}";

        // Perform the request
        mockMvc.perform(post("/api/library/borrow")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().string("Book borrowed successfully: Sample Book"));
    }

    @Test
    public void testReturnBookSuccess() throws Exception {
        // Prepare test data
        Book book = new Book();
        book.setId(101L);
        book.setIsbn("1234567890");
        book.setTitle("Test Book");
        book.setAuthor("Test Author");

        // Mock the service call
        Mockito.when(libraryService.returnBook(Mockito.anyLong(), Mockito.anyLong())).thenReturn(book);

        // Create the request body
        String requestBody = "{\"borrowerId\":2,\"bookId\":3}";

        // Perform the request
        mockMvc.perform(post("/api/library/return")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().string("Book returned successfully: Sample Book"));
    }

    @Test
    public void testBorrowBookError() throws Exception {
        // Mock the service call to throw an exception
        Mockito.when(libraryService.borrowBook(Mockito.anyLong(), Mockito.anyLong())).thenThrow(new Exception("Book not available"));

        // Create the request body
        String requestBody = "{\"borrowerId\":2,\"bookId\":4}";

        // Perform the request
        mockMvc.perform(post("/api/library/borrow")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().string("Error: Book not available"));
    }

    @Test
    public void testReturnBookError() throws Exception {
        // Mock the service call to throw an exception
        Mockito.when(libraryService.returnBook(Mockito.anyLong(), Mockito.anyLong())).thenThrow(new Exception("Return failed"));

        // Create the request body
        String requestBody = "{\"borrowerId\":2,\"bookId\":3}";

        // Perform the request
        mockMvc.perform(post("/api/library/return")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().string("Error: Book not borrowed"));
    }
}
