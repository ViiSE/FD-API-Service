package ru.fd.api.service.exception;

public class CreatorException extends Exception {

    public CreatorException(String message) {
        super(message);
    }

    public CreatorException(String message, Throwable cause) {
        super(message, cause);
    }
}
