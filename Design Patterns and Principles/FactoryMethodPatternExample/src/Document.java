package com.example.factory;

/**
 * Common interface implemented by every kind of document
 * our document-management system can produce.
 */
public interface Document {
    void open();
    void save();
    String getType();
}
