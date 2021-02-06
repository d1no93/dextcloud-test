package com.shaazabedin.service;

import com.shaazabedin.db.BookRepository;
import com.shaazabedin.domain.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    private BookService bookService;

    @Before
    public void setUp() {
        bookService = new BookServiceImpl(bookRepository);
    }

    @Test
    public void shouldSaveBook() {
        Book book = new Book();
        bookService.saveBook(book);
        verify(bookRepository, only()).save(book);
    }

    @Test
    public void shouldFindBook() {
        Book expectedBook = new Book();
        expectedBook.setIsbn("ISBN");

        when(bookRepository.findById("ISBN")).thenReturn(Optional.of(expectedBook));

        Book actualBook = bookService.findBook("ISBN");
        assertThat(actualBook.getIsbn()).isEqualTo(expectedBook.getIsbn());
    }

    @Test
    public void shouldUpdateBook() {

        Book book = new Book();
        book.setIsbn("ISBN");
        when(bookRepository.findById("ISBN")).thenReturn(Optional.of(book));

        bookService.updateBook(book);
        verify(bookRepository).save(book);
    }

    @Test
    public void shouldRemoveBook() {

        Book expectedBook = new Book();
        expectedBook.setIsbn("ISBN");
        when(bookRepository.findById("ISBN")).thenReturn(Optional.of(expectedBook));

        bookService.removeBook("ISBN");
        verify(bookRepository).deleteById("ISBN");
    }

    @Test
    public void shouldGetNoOfAuthors() {
        List<Book> books = Arrays.asList(
                createBook("ISBN1", "Author1"),
                createBook("ISBN2", "Author2"),
                createBook("ISBN3", "Author2"));

        when(bookRepository.findAll()).thenReturn(books);
        Long noOfBooksByAuthor2 = bookService.getBooksByAuthor("Author2");
        assertThat(noOfBooksByAuthor2).isEqualTo(2);
    }

    private Book createBook(String isbn, String author) {
        Book book = new Book();
        book.setIsbn(isbn);
        book.setAuthor(author);
        book.setTitle("Title");
        book.setSummary("Summary");
        return book;
    }
}