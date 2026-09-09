package com.telai.api.kernel.domain.refs;

public record UserReference(String id) {
    public UserReference {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
    }
}
