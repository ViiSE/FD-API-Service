package ru.fd.api.service.producer.repository;

import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.repository.ProductsRepository;

public interface ProductsRepositoryProducer {
    ProductsRepository getProductsRepositorySimpleInstance();
    ProductsRepository getProductsRepositoryWithAttributesInstance(
            ProductsRepository productsRepository, ProductProducer productProducer);
    ProductsRepository getProductsRepositoryWithBalancesInstance(
            ProductsRepository productsRepository, ProductProducer productProducer);
    ProductsRepository getProductsRepositoryWithPricesInstance(
            ProductsRepository productsRepository, ProductProducer productProducer);
    ProductsRepository getProductsRepositoryWithStatusesInstance(
            ProductsRepository productsRepository, ProductProducer productProducer);
    ProductsRepository getProductsRepositoryWithShortDescriptionInstance(
            ProductsRepository productsRepository, ProductProducer productProducer);
    ProductsRepository getProductsRepositoryWithFullDescriptionInstance(
            ProductsRepository productsRepository, ProductProducer productProducer);

}
