package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exercise 1: Logging Error Messages and Warning Levels using SLF4J.
 */
public class LoggingExample {

    private static final Logger logger = LoggerFactory.getLogger(LoggingExample.class);

    public static void main(String[] args) {
        logger.error("This is an error message");
        logger.warn("This is a warning message");
    }
}
