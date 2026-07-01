package com.example;

/**
 * A service that depends on an external API.
 */
public class MyService {

    private final ExternalApi externalApi;

    public MyService(ExternalApi externalApi) {
        this.externalApi = externalApi;
    }

    /**
     * Fetches data from the external API.
     */
    public String fetchData() {
        return externalApi.getData();
    }

    /**
     * Fetches data for a specific id from the external API.
     */
    public String fetchDataById(String id) {
        return externalApi.getDataById(id);
    }
}
