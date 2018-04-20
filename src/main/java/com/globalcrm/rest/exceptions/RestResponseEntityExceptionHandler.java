package com.globalcrm.rest.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Hugo Lezama on April - 2018
 */
@Slf4j
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException e, WebRequest request) {
        log.error("Handling ResourceNotFoundException...");
        return handleExceptionInternal(e, new ErrorDetails(LocalDateTime.now(), Stream.of("Resource Not Found").collect(Collectors.toList()), e.getMessage()), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleNumberFormatException(MethodArgumentTypeMismatchException ex, WebRequest
            request) {
        log.error("Handling MethodArgumentTypeMismatchException... ");
        return handleExceptionInternal(ex, new ErrorDetails(LocalDateTime.now(), Stream.of("Argument type mismatch").collect(Collectors.toList()), ex.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest
            request) {
        log.error("Handling ConstraintViolationException... ");

        return handleExceptionInternal(ex, new ErrorDetails(LocalDateTime.now(), Stream.of("Constraint Validation")
                .collect(Collectors.toList()), ex.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders
            headers, HttpStatus status, WebRequest request) {
        log.error("Handling MethodArgumentNotValidException...");
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return handleExceptionInternal(ex, new ErrorDetails(LocalDateTime.now(),fieldErrors.stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList()), ex.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

    }

}
