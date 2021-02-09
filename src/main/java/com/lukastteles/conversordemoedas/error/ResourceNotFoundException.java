package com.lukastteles.conversordemoedas.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Resource not found exception class
 * @author Lukas Teles
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Defaut constructor
     * @param message {@link java.lang.String} exception message
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }

    /**
     * Clear stack trace from object
     * @return {@link java.lang.Throwable}
     */
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
