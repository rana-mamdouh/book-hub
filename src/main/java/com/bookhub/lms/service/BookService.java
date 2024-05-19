package com.bookhub.lms.service;

import com.bookhub.lms.dto.BookDTO;
import com.bookhub.lms.entity.Book;
import com.bookhub.lms.exception.ResourceNotFoundException;
import com.bookhub.lms.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;


    @Autowired
    private ModelMapper modelMapper;

    public List<BookDTO> findAll() {
        List<Book> books = bookRepository.findAll();
        if (books.isEmpty()) {
            throw new ResourceNotFoundException("No books found");
        }
        return books.stream()
                .map(this::mapToBookDTO)
                .collect(Collectors.toList());
    }

    public BookDTO findById(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            return mapToBookDTO(book);
        }
        throw new ResourceNotFoundException("Book not found with ID: " + id);
    }

    public Book save(Long id, BookDTO bookDTO) {
        Book book = mapToBook(bookDTO);
        if (id != null) {
            Book existingBook = bookRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Book not found with ID: " + id));
            book.setId(existingBook.getId());
        }
        return bookRepository.save(book);
    }

    public void deleteById(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book not found with ID: " + id);
        }
        bookRepository.deleteById(id);
    }

    private Book mapToBook(BookDTO bookDTO) {
        return modelMapper.map(bookDTO, Book.class);
    }

    private BookDTO mapToBookDTO(Book book) {
        return modelMapper.map(book, BookDTO.class);
    }
}
