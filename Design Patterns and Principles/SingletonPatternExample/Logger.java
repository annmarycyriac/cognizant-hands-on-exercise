package com.example.singleton;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Logger {

    private static volatile Logger instance;

    private int logCount;

    private final SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // The constructor is PRIVATE: nobody outside this class can call `new Logger()`.
    private Logger() {
        logCount = 0;
        System.out.println("[Logger] A new Logger instance has been created. (This should print only once!)");
    }

  
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
