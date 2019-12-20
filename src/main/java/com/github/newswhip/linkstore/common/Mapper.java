package com.github.newswhip.linkstore.common;

import com.fasterxml.jackson.databind.ObjectMapper;

public enum Mapper {
    INSTANCE;
    private final ObjectMapper objectMapper = new ObjectMapper();

    Mapper(){
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}