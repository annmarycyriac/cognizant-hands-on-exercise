package com.library.service;

import com.library.repository.BookRepository;

public class BookService {

    private BookRepository bookRepository;

   
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
