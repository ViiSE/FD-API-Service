package ru.fd.api.service.entity;

public interface Products {
    Product findProductById(String id);
    void decorateProduct(String id, Product product);
    Object formForSend();
}
