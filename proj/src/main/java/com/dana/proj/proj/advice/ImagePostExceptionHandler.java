package com.dana.proj.proj.advice;

import com.dana.proj.proj.exception.BadFileNameException;
import com.dana.proj.proj.exception.NameTooLongException;
import com.dana.proj.proj.exception.WritingImageLocalException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ImagePostExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadFileNameException.class)
    public ResponseEntity<Object> handleBadFileName(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Sorry! Filename contains invalid path sequence";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
        //return new ResponseEntity<String>(bodyOfResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NameTooLongException.class)
    public ResponseEntity<String> handleNameTooLong(RuntimeException ex) {
        return new ResponseEntity<String>("Sorry, the name submitted is too long!", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElement(NoSuchElementException ex) {
        return new ResponseEntity<String>("Sorry, Image not found!", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WritingImageLocalException.class)
    public ResponseEntity<String> handleWritingImageLocal(WritingImageLocalException ex) {
        return new ResponseEntity<String>("Sorry, there was an issue when saving image locally!", HttpStatus.BAD_REQUEST);
    }


}
