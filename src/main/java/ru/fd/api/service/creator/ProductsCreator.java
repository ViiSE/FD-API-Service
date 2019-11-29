package ru.fd.api.service.creator;

import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.ProductsCreatorException;

public interface ProductsCreator {
    Products create() throws ProductsCreatorException;
}
