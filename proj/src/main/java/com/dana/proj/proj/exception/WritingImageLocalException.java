package com.dana.proj.proj.exception;

public class WritingImageLocalException extends RuntimeException {

    private String errorMessage;

    public WritingImageLocalException() {
        super();
    }

    public WritingImageLocalException(String message) {
        super();
        this.errorMessage = message;
    }
}
