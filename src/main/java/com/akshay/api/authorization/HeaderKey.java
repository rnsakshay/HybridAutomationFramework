package com.akshay.api.authorization;

public enum HeaderKey {

    AUTHORIZATION("Authorization"),
    CONTENT_TYPE("Content-Type"),
    ACCEPT("Accept");

    private final String value;

    HeaderKey(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
