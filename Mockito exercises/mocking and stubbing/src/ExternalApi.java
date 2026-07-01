package com.example;

/**
 * Represents an external API dependency that MyService relies on.
 * In a real application this might make an HTTP call, hit a database, etc.
 */
public interface ExternalApi {

    /**
     * Fetches some data with no arguments.
     */
    String getData();

    /**
     * Fetches data for a specific id.
     */
    String getDataById(String id);
}
