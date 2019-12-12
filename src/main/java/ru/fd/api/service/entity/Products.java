package ru.fd.api.service.entity;

public interface Products extends Iterable<Product>, Sender {
    Product findProductById(String id);
    void decorateProduct(String id, Product product);
    void decorateProduct(int key, Product product);
    void removeProducts(Class<? extends Product> decorateProduct);
}
