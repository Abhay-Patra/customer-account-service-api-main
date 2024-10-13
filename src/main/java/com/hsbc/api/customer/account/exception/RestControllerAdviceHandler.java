package com.hsbc.api.customer.account.exception;

import com.hsbc.api.customer.account.util.ApplicationUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestControllerAdviceHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest webRequest)
            throws UnsupportedOperationException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json;charset=UTF-8");
        ErrorResponse errorResponse = ApplicationUtil.populateConstraintViolationException();
        return new ResponseEntity<>(errorResponse, httpHeaders, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json;charset=UTF-8");
        ErrorResponse errorResponse = ApplicationUtil.notFound();
        return new ResponseEntity<>(errorResponse, httpHeaders, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json;charset=UTF-8");
        ErrorResponse errorResponse = ApplicationUtil.unsupportedMediaType();
        return new ResponseEntity<>(errorResponse, httpHeaders, HttpStatus.NOT_ACCEPTABLE);
    }

    @Override
    public ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json;charset=UTF-8");
        ErrorResponse errorResponse = ApplicationUtil.populateMethodNotAllowedException();
        return new ResponseEntity<>(errorResponse, httpHeaders, HttpStatus.METHOD_NOT_ALLOWED);
    }
}
