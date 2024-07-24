package com.bookstore.controller;

import com.bookstore.model.Book;
import com.bookstore.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    public BookControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllBooks() {
        List<Book> books = Arrays.asList(new Book("Book 1", "Author 1", 10.0), new Book("Book 2", "Author 2", 15.0));
        when(bookService.getAllBooks()).thenReturn(books);

        ResponseEntity<List<Book>> response = bookController.getAllBooks();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void testGetBookById() {
        Book book = new Book("Book 1", "Author 1", 10.0);
        when(bookService.getBookById(1L)).thenReturn(book);

        ResponseEntity<Book> response = bookController.getBookById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(book, response.getBody());
    }

    @Test
    public void testGetBookById_NotFound() {
        when(bookService.getBookById(1L)).thenReturn(null);

        ResponseEntity<Book> response = bookController.getBookById(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testCreateBook() {
        Book book = new Book("Book 1", "Author 1", 10.0);
        when(bookService.createBook(book)).thenReturn(book);

        ResponseEntity<Book> response = bookController.createBook(book);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(book, response.getBody());
    }

    @Test
    public void testUpdateBook() {
        Book book = new Book("Book 1", "Author 1", 10.0);
        when(bookService.updateBook(1L, book)).thenReturn(book);

        ResponseEntity<Book> response = bookController.updateBook(1L, book);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(book, response.getBody());
    }

    @Test
    public void testUpdateBook_BadRequest() {
        Book book = new Book("Book 1", "Author 1", 10.0);
        when(bookService.updateBook(1L, book)).thenReturn(null);

        ResponseEntity<Book> response = bookController.updateBook(1L, book);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testDeleteBook() {
        when(bookService.deleteBook(1L)).thenReturn(true);

        ResponseEntity<Void> response = bookController.deleteBook(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testDeleteBook_NotFound() {
        when(bookService.deleteBook(1L)).thenReturn(false);

        ResponseEntity<Void> response = bookController.deleteBook(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
