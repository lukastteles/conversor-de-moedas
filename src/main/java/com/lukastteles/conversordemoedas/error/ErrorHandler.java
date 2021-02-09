package com.lukastteles.conversordemoedas.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Error handler configuration
 * @author Lukas Teles
 */
@RestControllerAdvice
public class ErrorHandler {

    /**
     * Handle {@link com.lukastteles.conversordemoedas.error.ResourceNotFoundException} object
     * @param resourceNotFoundException managed exception
     * @return {@link com.lukastteles.conversordemoedas.error.ResourceNotFoundException}
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResourceNotFoundException handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException) {
        return  resourceNotFoundException;
    }

}
