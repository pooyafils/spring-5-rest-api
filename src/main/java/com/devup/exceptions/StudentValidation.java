package com.devup.exceptions;

public class StudentValidation extends RuntimeException {
    public StudentValidation(String message) {
        super(message);
    }

    public StudentValidation(String message, Throwable cause) {
        super(message, cause);
    }

    public StudentValidation(Throwable cause) {
        super(cause);
    }
}
