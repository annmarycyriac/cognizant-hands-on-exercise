package com.library.repository;

/**
 * Exercise 1: Repository class responsible for data access for Book records.
 * In a real application this would talk to a database; here it simulates
 * that with a simple in-memory implementation so the Spring wiring can be
 * demonstrated end to end.
 */
public class BookRepository {

    public BookRepository() {
        System.out.println("BookRepository: instance created by Spring container.");
    }

    /**
     * Simulates fetching book data from a data source.
     */
    public String findBookById(String id) {
        return "Book[id=" + id + ", title=Effective Java]";
    }
}
