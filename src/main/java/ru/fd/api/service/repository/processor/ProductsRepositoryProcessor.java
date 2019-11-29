package ru.fd.api.service.repository.processor;

import ru.fd.api.service.repository.ProductsRepository;

import java.util.function.Function;

public interface ProductsRepositoryProcessor extends Function<ProductsRepository, ProductsRepository> {
}
