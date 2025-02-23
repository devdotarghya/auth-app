package org.arghya.auth_app.exception;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({Exception.class})
    public HttpEntity<ServiceErrorResponse> handleGenericException(Exception e) {
        ServiceErrorResponse serviceErrorResponse = ServiceErrorResponse.builder()
                .errorMessage(e.getMessage()).build();
        return new ResponseEntity<>(serviceErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
