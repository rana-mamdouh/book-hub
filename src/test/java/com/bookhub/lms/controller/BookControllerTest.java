package com.bookhub.lms.controller;

import com.bookhub.lms.dto.BookDTO;
import com.bookhub.lms.entity.Book;
import com.bookhub.lms.service.BookService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllBooks() {
        List<BookDTO> expectedBooks = Arrays.asList(
                new BookDTO(),
                new BookDTO()
        );
        when(bookService.findAll()).thenReturn(expectedBooks);

        List<BookDTO> actualBooks = bookController.getAllBooks();

        Assert.assertEquals(actualBooks, expectedBooks);
    }

    @Test
    public void testGetBook() {
        Long id = 1L;
        BookDTO expectedBook = new BookDTO();
        when(bookService.findById(id)).thenReturn(expectedBook);

        BookDTO actualBook = bookController.getBook(id);

        Assert.assertEquals(actualBook, expectedBook);
    }

    @Test
    public void testAddBook() {
        BookDTO bookDTO = new BookDTO();
        Book savedBook = new Book();
        when(bookService.save(isNull(), eq(bookDTO))).thenReturn(savedBook);
        Book actualBook = bookController.addBook(bookDTO);
        Assert.assertEquals(actualBook, savedBook);
    }

    @Test
    public void testUpdateBook() {
        Long id = 1L;
        BookDTO bookDTO = new BookDTO();
        Book updatedBook = new Book();
        when(bookService.save(eq(id), eq(bookDTO))).thenReturn(updatedBook);
        Book actualBook = bookController.updateBook(id, bookDTO);
        Assert.assertEquals(actualBook, updatedBook);
    }

    @Test
    public void testDeleteBook() {
        Long id = 1L;
        doNothing().when(bookService).deleteById(eq(id));
        bookController.deleteBook(id);
        verify(bookService, times(1)).deleteById(id);
    }
}
