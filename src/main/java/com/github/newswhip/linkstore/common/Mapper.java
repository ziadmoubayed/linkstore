package com.github.newswhip.linkstore.common;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Singleton Mapper. We can add mapper configuration in the constructor
 */
public enum Mapper {
    INSTANCE;
    private final ObjectMapper objectMapper = new ObjectMapper();

    Mapper() {
    }

    public ObjectMapper getMapper() {
        return objectMapper;
    }
}