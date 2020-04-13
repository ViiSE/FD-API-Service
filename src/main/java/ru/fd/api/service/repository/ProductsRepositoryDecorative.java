package ru.fd.api.service.repository;

import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.RepositoryException;

public interface ProductsRepositoryDecorative {
    Products read(Products products) throws RepositoryException;
}
