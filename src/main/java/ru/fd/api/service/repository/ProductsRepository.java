package ru.fd.api.service.repository;

import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.exception.RepositoryException;

public interface ProductsRepository {
    Products readProducts() throws RepositoryException;
}
