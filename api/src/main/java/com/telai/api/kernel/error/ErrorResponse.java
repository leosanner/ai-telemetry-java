package com.telai.api.kernel.error;

public record ErrorResponse(
        String message,
        String cause,
        StackTraceElement[] stackTrace
) {
    public static ErrorResponse fromException(Exception e) {
        return new ErrorResponse(
                e.getMessage(),
                e.getCause() != null ? e.getCause().getMessage() : null,
                e.getStackTrace()
        );
    }
}
