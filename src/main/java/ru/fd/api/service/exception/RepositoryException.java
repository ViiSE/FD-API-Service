package ru.fd.api.service.exception;

public class RepositoryException extends ExceptionWithSendMessage {

    public RepositoryException(String message) {
        super(message, message);
    }

    public RepositoryException(String messageForSend, String message) {
        super(messageForSend, message);
    }

    public RepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
