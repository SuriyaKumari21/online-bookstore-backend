package com.bookstore.service;

import com.bookstore.model.Book;
import com.bookstore.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    public BookServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllBooks() {
        List<Book> books = Arrays.asList(new Book("Book 1", "Author 1", 10.0), new Book("Book 2", "Author 2", 15.0));
        when(bookRepository.findAll()).thenReturn(books);

        List<Book> result = bookService.getAllBooks();
        assertEquals(2, result.size());
    }

    @Test
    public void testGetBookById() {
        Book book = new Book("Book 1", "Author 1", 10.0);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        Book result = bookService.getBookById(1L);
        assertEquals(book, result);
    }

    @Test
    public void testCreateBook() {
        Book book = new Book("Book 1", "Author 1", 10.0);
        when(bookRepository.save(book)).thenReturn(book);

        Book result = bookService.createBook(book);
        assertEquals(book, result);
    }

    @Test
    public void testUpdateBook() {
        Book book = new Book("Book 1", "Author 1", 10.0);
        when(bookRepository.existsById(1L)).thenReturn(true);
        when(bookRepository.save(book)).thenReturn(book);

        Book result = bookService.updateBook(1L, book);
        assertEquals(book, result);
    }

    @Test
    public void testDeleteBook() {
        when(bookRepository.existsById(1L)).thenReturn(true);

        boolean result = bookService.deleteBook(1L);
        assertEquals(true, result);
    }
}
