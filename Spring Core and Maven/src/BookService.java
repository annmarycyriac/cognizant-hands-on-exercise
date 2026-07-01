package com.library.service;

import com.library.repository.BookRepository;

/**
 * Exercise 1: Service class for the library's book operations.
 * Exercise 2: Depends on BookRepository, wired in via Setter Injection
 * (Spring's IoC container calls setBookRepository(...) based on the
 * <property> tag in applicationContext.xml).
 */
public class BookService {

    private BookRepository bookRepository;

    // Exercise 2: setter used by Spring for Dependency Injection.
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        System.out.println("BookService: BookRepository injected via setter.");
    }

    public String getBookDetails(String id) {
        if (bookRepository == null) {
            throw new IllegalStateException("BookRepository has not been injected!");
        }
        return bookRepository.findBookById(id);
    }
}
