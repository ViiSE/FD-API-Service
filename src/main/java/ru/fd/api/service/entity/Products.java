package ru.fd.api.service.entity;

public interface Products extends Iterable<Product> {
    Product findProductById(String id);
    void decorateProduct(String id, Product product);
    Object formForSend();
}
