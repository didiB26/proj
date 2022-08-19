package com.dana.proj.proj.advice;

import com.dana.proj.proj.exception.BadFileNameException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ImagePostExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = BadFileNameException.class)
    public ResponseEntity<Object> handleBadFileName(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Sorry! Filename contains invalid path sequence";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }


}
