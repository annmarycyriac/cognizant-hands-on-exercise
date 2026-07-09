package com.example.singleton;


public class LoggerTest {

    public static void main(String[] args) {
        System.out.println("=== Singleton Pattern Demo: Logger ===\n");

        // Grab the logger from two different "places" in the application.
        Logger logger1 = Logger.getInstance();
        logger1.log("Application started.");

        Logger logger2 = Logger.getInstance();
        logger2.log("Connecting to database...");

        Logger logger3 = Logger.getInstance();
        logger3.log("User authentication succeeded.");

        System.out.println();
        System.out.println("logger1 hashCode: " + System.identityHashCode(logger1));
        System.out.println("logger2 hashCode: " + System.identityHashCode(logger2));
        System.out.println("logger3 hashCode: " + System.identityHashCode(logger3));

        System.out.println();
        if (logger1 == logger2 && logger2 == logger3) {
            System.out.println("SUCCESS: logger1, logger2 and logger3 are the SAME instance.");
        } else {
            System.out.println("FAILURE: multiple instances were created!");
        }

        System.out.println("Total log entries recorded by the single Logger instance: "
                + logger1.getLogCount());
    }
}
