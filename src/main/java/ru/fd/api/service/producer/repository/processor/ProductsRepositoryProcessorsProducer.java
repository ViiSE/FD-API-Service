package ru.fd.api.service.producer.repository.processor;

import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.producer.repository.ProductsRepositoryProducer;
import ru.fd.api.service.repository.processor.ProductsRepositoryProcessors;

public interface ProductsRepositoryProcessorsProducer {
    ProductsRepositoryProcessors getProductsRepositoryProcessorsSingletonImpl(
            ProductsRepositoryProducer prodsRepoProducer, ProductProducer prodProducer);
}
