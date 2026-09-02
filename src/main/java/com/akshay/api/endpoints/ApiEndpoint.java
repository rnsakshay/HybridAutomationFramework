package com.akshay.api.endpoints;

public enum ApiEndpoint {

    USERS("/api/users"),
    LOGIN("/api/login"),
    REGISTER("/api/register");

    private final String path;

    ApiEndpoint(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
