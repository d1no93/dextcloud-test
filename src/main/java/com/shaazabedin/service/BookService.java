package com.shaazabedin.service;

import com.shaazabedin.domain.Book;

public interface BookService {

    void saveBook(Book book);

    Book findBook(String isbn);

    void updateBook(Book book);

    void removeBook(String isbn);

    Long getBooksByAuthor(String author);
}
