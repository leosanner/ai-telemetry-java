package com.telai.api.kernel.error.exceptions;

public class InvariantError extends RuntimeException {
    public InvariantError(String message) {
        super(message);
    }
}
