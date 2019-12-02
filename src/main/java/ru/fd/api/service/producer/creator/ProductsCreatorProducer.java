package ru.fd.api.service.producer.entity;

import ru.fd.api.service.creator.ProductsCreator;
import ru.fd.api.service.entity.Products;

import java.util.List;

public interface ProductsCreatorProducer {
    ProductsCreator getProductsCreatorDefaultInstance(List<String> with);
}
