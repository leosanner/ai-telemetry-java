package com.telai.api.kernel.error.exceptions;

public class ToolError extends RuntimeException {
    public ToolError(String message, Throwable cause) {
        super(message, cause);
    }
}
