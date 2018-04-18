package com.globalcrm.rest.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

/**
 * Created by Hugo Lezama on April - 2018
 */
@Slf4j
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException e, WebRequest request) {
        log.error("Handling ResourceNotFoundException...");
        return handleExceptionInternal(e, new ErrorDetails(LocalDateTime.now(), "Resource Not Found", e.getMessage()), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleNumberFormatException(MethodArgumentTypeMismatchException ex, WebRequest
            request) {
        log.error("Handling MethodArgumentTypeMismatchException... ");

        return handleExceptionInternal(ex, new ErrorDetails(LocalDateTime.now(), "Argument type mismatch", ex.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        log.error("Handling MethodArgumentNotValidException...");
        return handleExceptionInternal(ex, new ErrorDetails(LocalDateTime.now(), "Argument Not Valid", ex.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

    }

}
