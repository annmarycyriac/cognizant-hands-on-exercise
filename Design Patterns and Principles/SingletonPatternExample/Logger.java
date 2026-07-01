package com.example.singleton;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Logger implements the Singleton design pattern.
 * It guarantees that only ONE instance of Logger ever exists
 * throughout the application's lifecycle, providing a single,
 * consistent point for writing log messages.
 */
public class Logger {

    // The single, shared instance of Logger.
    // 'volatile' ensures visibility across threads for the double-checked locking below.
    private static volatile Logger instance;

    // Keeps a running count of how many log messages have been written,
    // just so we can prove in the test class that state is shared.
    private int logCount;

    private final SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // The constructor is PRIVATE: nobody outside this class can call `new Logger()`.
    private Logger() {
        logCount = 0;
        System.out.println("[Logger] A new Logger instance has been created. (This should print only once!)");
    }

    /**
     * Public, static access point for the single instance.
     * Uses double-checked locking so it is both lazy (instance is created
     * only when first needed) and thread-safe.
     */
    public static Logger getInstance() {
        if (instance == null) {                 // first check (no locking, fast path)
            synchronized (Logger.class) {
                if (instance == null) {          // second check (inside the lock)
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    public void log(String message) {
        logCount++;
        String timestamp = timestampFormat.format(new Date());
        System.out.println("[" + timestamp + "] LOG #" + logCount + ": " + message);
    }

    public int getLogCount() {
        return logCount;
    }
}
