package com.uterm.web;

import java.net.MalformedURLException;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


/**
 * APIErrorHandler
 */
@ControllerAdvice
public class APIErrorHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(ResponseEntityExceptionHandler.class);

    @ExceptionHandler({ MalformedURLException.class, ConstraintViolationException.class })
    public ResponseEntity<Object> badRequest(RuntimeException e, WebRequest request) {
        log.error(e.getMessage(), e);
        return handleExceptionInternal(e, "check the request input", 
            new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ NullPointerException.class })
    public ResponseEntity<Object> internalServerError(RuntimeException e, WebRequest request) {
        log.error(e.getMessage(), e);
        return handleExceptionInternal(e, null, 
            new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}