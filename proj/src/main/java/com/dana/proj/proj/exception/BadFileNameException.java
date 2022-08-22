package com.dana.proj.proj.exception;
public class BadFileNameException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String errorMessage;

    public BadFileNameException(String message) {
        super();
        this.errorMessage = message;
    }
}
