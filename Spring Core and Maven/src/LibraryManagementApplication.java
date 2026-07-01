package com.library;

import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Exercise 1: Loads the Spring application context to verify the
 * configuration.
 * Exercise 2: Retrieves the BookService bean (with its BookRepository
 * dependency already injected by Spring) and calls it, proving that
 * Dependency Injection worked.
 */
public class LibraryManagementApplication {

    public static void main(String[] args) {
        // Load the Spring IoC container using the XML configuration.
        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        // Retrieve the BookService bean - Spring has already injected
        // BookRepository into it via the setter defined in the XML.
        BookService bookService = context.getBean("bookService", BookService.class);

        // Use the service - this call reaches BookRepository through
        // the injected dependency.
        String details = bookService.getBookDetails("101");
        System.out.println("Result: " + details);
    }
}
