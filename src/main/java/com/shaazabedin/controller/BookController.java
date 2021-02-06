package com.shaazabedin.controller;

import com.shaazabedin.domain.Book;
import com.shaazabedin.service.BookServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Slf4j
@RequestMapping(path = "/book")
@RestController
@EnableWebMvc
public class BookController {

    private final BookServiceImpl bookService;

    @Autowired
    public BookController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.ALL_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void createBook(@RequestBody Book book) {
        this.bookService.saveBook(book);
    }

    @GetMapping(value = "/isbn/{isbn}")
    @ResponseBody
    public ResponseEntity<Book> findBook(@PathVariable(value = "isbn") String isbn) {
        Book book = this.bookService.findBook(isbn);
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateBook(@RequestBody Book book) {
        this.bookService.updateBook(book);
    }

    @DeleteMapping(value = "/isbn/{isbn}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBook(@PathVariable(value = "isbn") String isbn) {
        this.bookService.removeBook(isbn);
    }

    @GetMapping(value = "/author/{author}/count")
    @ResponseBody
    public ResponseEntity<Long> findNumberOfBooksByAuthor(@PathVariable(value = "author") String author) {
        Long noOfBooksByAuthor = this.bookService.getBooksByAuthor(author);
        return ResponseEntity.status(HttpStatus.OK).body(noOfBooksByAuthor);
    }
}
