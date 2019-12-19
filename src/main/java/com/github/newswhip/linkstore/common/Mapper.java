package com.github.newswhip.linkstore.common;

import com.fasterxml.jackson.databind.ObjectMapper;

public enum Mapper {
    INSTANCE;
    private final ObjectMapper objectMapper = new ObjectMapper();

    Mapper(){
        // TODO add configuration on the ObjectMapper here.
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}