package ru.fd.api.service.exception;

public class ProcessException extends Exception {

    public ProcessException(String message) {
        super(message);
    }

    public ProcessException(String message, Exception ex) {
        super(message, ex);
    }
}
