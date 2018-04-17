package com.globalcrm.rest.controllers;

import com.globalcrm.rest.services.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by Hugo Lezama on April - 2018
 */
@Slf4j
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(Exception e, WebRequest request){
        log.error("Handling ResourceNotFoundException...");
        return new ResponseEntity<>("Resource Not Found", new HttpHeaders(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<Object> handleNumberFormatException(Exception e, WebRequest request){
        log.error("Handling NumberFormatException...");
        return new ResponseEntity<>("Not A Number", new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
