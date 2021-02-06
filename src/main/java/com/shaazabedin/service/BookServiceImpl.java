package com.shaazabedin.service;

import com.shaazabedin.db.BookRepository;
import com.shaazabedin.domain.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void saveBook(Book book) {
        log.info("Begin - Save Book: {}", book);
        this.bookRepository.save(book);
        log.info("End - Save Book: {}", book);
    }

    @Override
    public Book findBook(String isbn) {
        Optional<Book> bookToFind = bookRepository.findById(isbn);
        if (bookToFind.isPresent()) {
            log.info("Find book - ISBN: {}", isbn);
            return bookToFind.get();
        } else {
            log.warn("No book with ISBN: {}, Aborting update", isbn);
            return null;
        }
    }

    @Override
    public void updateBook(Book book) {
        Optional<Book> bookToUpdate = bookRepository.findById(book.getIsbn());
        if (bookToUpdate.isPresent()) {
            log.info("Updating Book -- Original: {}, New: {}", bookToUpdate.get(), book);
            this.bookRepository.save(book);
        } else {
            log.warn("No book with ISBN: {}, Aborting update", book.getIsbn());
        }
    }

    @Override
    public void removeBook(String isbn) {
        Optional<Book> bookToDelete = bookRepository.findById(isbn);
        if (bookToDelete.isPresent()) {
            log.info("Deleting Book: {}", bookToDelete.get());
            this.bookRepository.deleteById(isbn);
        } else {
            log.warn("No book with ISBN: {}, Aborting delete", isbn);
        }
    }

    @Override
    public Long getBooksByAuthor(String author) {
        log.info("Retrieve number of books for author: {}", author);
        return bookRepository.findAll().stream().filter(book -> book.getAuthor().equals(author)).count();
    }
}
