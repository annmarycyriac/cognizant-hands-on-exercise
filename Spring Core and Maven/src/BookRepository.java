package com.library.repository;


public class BookRepository {

    public BookRepository() {
        System.out.println("BookRepository: instance created by Spring container.");
    }

    public String findBookById(String id) {
        return "Book[id=" + id + ", title=Effective Java]";
    }
}
