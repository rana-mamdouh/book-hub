package com.bookhub.lms.controller;

import com.bookhub.lms.dto.BookDTO;
import com.bookhub.lms.entity.Book;
import com.bookhub.lms.service.BookService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@Validated
@NoArgsConstructor
@AllArgsConstructor
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<BookDTO> getAllBooks() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public BookDTO getBook(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @PostMapping
    public Book addBook(@RequestBody @Valid BookDTO bookDTO) {
        return bookService.save(null, bookDTO);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody @Valid BookDTO bookDTO) {
        return bookService.save(id, bookDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
        return ResponseEntity.ok("Book with ID " + id + " deleted successfully");
    }

}
