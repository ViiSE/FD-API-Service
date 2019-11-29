package ru.fd.api.service.exception;

public class ProductsCreatorException extends Exception {

    public ProductsCreatorException(String message) {
        super(message);
    }

    public ProductsCreatorException(String message, Throwable cause) {
        super(message, cause);
    }
}
